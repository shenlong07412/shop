/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.shop;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.shopxx.CommonAttributes;
import net.shopxx.Message;
import net.shopxx.Setting;
import net.shopxx.Setting.CaptchaType;
import net.shopxx.entity.Area;
import net.shopxx.entity.BaseEntity.Save;
import net.shopxx.entity.Cart;
import net.shopxx.entity.Member;
import net.shopxx.entity.Member.Gender;
import net.shopxx.entity.MemberAttribute;
import net.shopxx.entity.MemberAttribute.Type;
import net.shopxx.entity.SafeKey;
import net.shopxx.service.AreaService;
import net.shopxx.service.CaptchaService;
import net.shopxx.service.CartService;
import net.shopxx.service.MailService;
import net.shopxx.service.MemberAttributeService;
import net.shopxx.service.MemberRankService;
import net.shopxx.service.MemberService;
import net.shopxx.service.RSAService;
import net.shopxx.service.TemplateService;
import net.shopxx.shiro.Principal;
import net.shopxx.util.SettingUtils;
import net.shopxx.util.WebUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 会员注册
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("shopRegisterController")
@RequestMapping("/register")
public class RegisterController extends BaseController {

    @Resource(name = "captchaServiceImpl")
    private CaptchaService         captchaService;
    @Resource(name = "rsaServiceImpl")
    private RSAService             rsaService;
    @Resource(name = "memberServiceImpl")
    private MemberService          memberService;
    @Resource(name = "memberRankServiceImpl")
    private MemberRankService      memberRankService;
    @Resource(name = "memberAttributeServiceImpl")
    private MemberAttributeService memberAttributeService;
    @Resource(name = "areaServiceImpl")
    private AreaService            areaService;
    @Resource(name = "cartServiceImpl")
    private CartService            cartService;
    @Resource(name = "mailServiceImpl")
    private MailService            mailService;
    @Resource(name = "templateServiceImpl")
    private TemplateService        templateService;

    // @Resource
    // private SendSmsService sendSmsService;

    /**
     * 检查用户名是否被禁用或已存在
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查E-mail是否存在
     */
    @RequestMapping(value = "/check_email", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        if (memberService.emailExists(email)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 注册页面1
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/register/1basic_info";
    }

    /**
     * 注册页面2
     */
    @RequestMapping(value = "/regist_step2", method = RequestMethod.GET)
    public String index2(Long memberId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        if (request.getSession().getAttribute("currentMemberSession") != null) {
            Member member = (Member) request.getSession().getAttribute("currentMemberSession");
            model.addAttribute("currentMemberModel", member);
            if ("1".equals(member.getRegistStep())) {// 当前步数是1
                return "/shop/register/2detail_info";
            } else if ("2".equals(member.getRegistStep())) {// 当前步数是2
                return "redirect:/register/regist_step3.jhtml";
            }
        }
        return "redirect:/register.jhtml";

    }

    /**
     * 注册页面3
     */
    @RequestMapping(value = "/regist_step3", method = RequestMethod.GET)
    public String index3(Long memberId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        if (request.getSession().getAttribute("currentMemberSession") != null) {
            Member member = (Member) request.getSession().getAttribute("currentMemberSession");
            if (member != null) {
                model.addAttribute("currentMemberModel", member);
                if ("1".equals(member.getRegistStep())) {// 当前步数是1
                    return "redirect:/register/regist_step2.jhtml";
                } else if ("2".equals(member.getRegistStep())) {// 当前步数是2
                    return "/shop/register/3activate";
                }
            }
        }
        return "redirect:/register.jhtml";
    }

    /**
     * 重新，发送激活邮件
     */
    @RequestMapping(value = "/send_activate_mail", method = { RequestMethod.GET, RequestMethod.POST })
    public String sendActivateMail(String username, HttpServletRequest request, HttpServletResponse response,
                                   ModelMap model) {
        Setting setting = SettingUtils.get();
        Member member = memberService.findByUsername(username);
        if (member == null) {
            return ERROR_VIEW;
        }
        
        SafeKey safeKey = new SafeKey();
        safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
        safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(),
                                                                                     setting.getSafeKeyExpiryTime()) : null);
        member.setSafeKey(safeKey);
        memberService.update(member);// 更新安全秘钥

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("member", member);
        param.put("domain", setting.getSiteUrl());// 以http开头
        net.shopxx.Template activateAccountMailTemplate = templateService.get("activateAccount");
        mailService.send(member.getEmail(), "帐号激活通知！", activateAccountMailTemplate.getTemplatePath(), param);// 发送邮件
        model.put("currentMemberModel", member);
        return "/shop/register/3activate";
    }

    /**
     * 激活账号
     */
    @RequestMapping(value = "/activate_account", method = { RequestMethod.GET, RequestMethod.POST })
    public String activateAccount(String username, String key, HttpServletRequest request,
                                  HttpServletResponse response, ModelMap model) {
        Member member = memberService.findByUsername(username);
        if (member == null) {
            return ERROR_VIEW;
        }
        SafeKey safeKey = member.getSafeKey();
        if (safeKey == null || safeKey.getValue() == null || !safeKey.getValue().equals(key)) {
            return ERROR_VIEW;
        }
        if (safeKey.hasExpired()) {
            model.addAttribute("erroInfo", Message.warn("shop.password.hasExpired"));
            return ERROR_VIEW;
        }
        member.setActivateEmail(true);
        memberService.update(member);
        Member newMember = memberService.findByUsername(username);
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        model.addAttribute("currentMemberModel", newMember);
        model.addAttribute("key", key);
        return "/shop/register/3activate";
    }

    /**
     * 注册提交
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody
    Message submit(String captchaId, String captcha, String username, String email, HttpServletRequest request,
                   HttpServletResponse response, HttpSession session) {
        String password = rsaService.decryptParameter("enPassword", request);
        rsaService.removePrivateKey(request);

        if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
            return Message.error("shop.captcha.invalid");
        }
        Setting setting = SettingUtils.get();
        if (!setting.getIsRegisterEnabled()) {
            return Message.error("shop.register.disabled");
        }
        if (!isValid(Member.class, "username", username, Save.class)
            || !isValid(Member.class, "password", password, Save.class)
            || !isValid(Member.class, "email", email, Save.class)) {
            return Message.error("shop.common.invalid");
        }
        if (username.length() < setting.getUsernameMinLength() || username.length() > setting.getUsernameMaxLength()) {
            return Message.error("shop.common.invalid");
        }
        if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
            return Message.error("shop.common.invalid");
        }
        if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
            return Message.error("shop.register.disabledExist");
        }
        if (!setting.getIsDuplicateEmail() && memberService.emailExists(email)) {
            return Message.error("shop.register.emailExist");
        }

        Member member = new Member();
        List<MemberAttribute> memberAttributes = memberAttributeService.findList();
        for (MemberAttribute memberAttribute : memberAttributes) {
            String parameter = request.getParameter("memberAttribute_" + memberAttribute.getId());
            if (memberAttribute.getType() == Type.name || memberAttribute.getType() == Type.address
                || memberAttribute.getType() == Type.zipCode || memberAttribute.getType() == Type.phone
                || memberAttribute.getType() == Type.mobile || memberAttribute.getType() == Type.text
                || memberAttribute.getType() == Type.select) {
                if (memberAttribute.getIsRequired() && StringUtils.isEmpty(parameter)) {
                    return Message.error("shop.common.invalid");
                }
                member.setAttributeValue(memberAttribute, parameter);
            } else if (memberAttribute.getType() == Type.gender) {
                Gender gender = StringUtils.isNotEmpty(parameter) ? Gender.valueOf(parameter) : null;
                if (memberAttribute.getIsRequired() && gender == null) {
                    return Message.error("shop.common.invalid");
                }
                member.setGender(gender);
            } else if (memberAttribute.getType() == Type.birth) {
                try {
                    Date birth = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter,
                                                                                         CommonAttributes.DATE_PATTERNS) : null;
                    if (memberAttribute.getIsRequired() && birth == null) {
                        return Message.error("shop.common.invalid");
                    }
                    member.setBirth(birth);
                } catch (ParseException e) {
                    return Message.error("shop.common.invalid");
                }
            } else if (memberAttribute.getType() == Type.area) {
                Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
                if (area != null) {
                    member.setArea(area);
                } else if (memberAttribute.getIsRequired()) {
                    return Message.error("shop.common.invalid");
                }
            } else if (memberAttribute.getType() == Type.checkbox) {
                String[] parameterValues = request.getParameterValues("memberAttribute_" + memberAttribute.getId());
                List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
                if (memberAttribute.getIsRequired() && (options == null || options.isEmpty())) {
                    return Message.error("shop.common.invalid");
                }
                member.setAttributeValue(memberAttribute, options);
            }
        }
        member.setUsername(username.toLowerCase());
        member.setPassword(DigestUtils.md5Hex(password));
        member.setEmail(email);
        member.setPoint(setting.getRegisterPoint());
        member.setAmount(new BigDecimal(0));
        member.setBalance(new BigDecimal(0));
        member.setIsEnabled(true);
        member.setIsLocked(false);
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
        member.setRegisterIp(request.getRemoteAddr());
        member.setLoginIp(request.getRemoteAddr());
        member.setLoginDate(new Date());
        member.setSafeKey(null);
        member.setMemberRank(memberRankService.findDefault());
        member.setFavoriteProducts(null);
        memberService.save(member);

        Cart cart = cartService.getCurrent();
        if (cart != null && cart.getMember() == null) {
            cartService.merge(member, cart);
            WebUtils.removeCookie(request, response, Cart.ID_COOKIE_NAME);
            WebUtils.removeCookie(request, response, Cart.KEY_COOKIE_NAME);
        }

        Map<String, Object> attributes = new HashMap<String, Object>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Entry<String, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }

        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername()));
        WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());

        return Message.success("shop.register.success");
    }

    /**********************************************************************************************************************/

    /**
     * 检查mobile是否存在
     */
    @RequestMapping(value = "/check_mobile", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        if (memberService.mobileExists(mobile)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 发送验证码
     */
    @RequestMapping(value = "/send_captcha", method = RequestMethod.GET)
    @ResponseBody
    public Message sendCaptcha(String mobile, HttpServletRequest request, HttpServletResponse response) {
        // String mobileNum = request.getParameter("mobile");
        // if (mobileNum != null && !"".equals(mobileNum)) {
        // return false;
        // }
        try {
            int randomCode = (int) (Math.random() * 900000) + 100000;
            request.getSession().setAttribute("randomCode", randomCode);
            // String content = "尊敬的用户您好，您在社区网注册的" + mobileNum + "账号，验证码为:" + randomCode;
            // SendResult result = sendSmsService.sendSms("site", null, null, new String[] { mobileNum }, content,
            // SendSmsService.TYPE_SMS, null, null);
            System.out.println("验证码>>>>>>>>>>>>>>>>>>>>" + randomCode);
            return Message.success("验证码发送成功" + randomCode);
        } catch (Exception e) {
            return Message.error("验证码发送失败");
        }

    }

    /**
     * 检查验证码是否正确
     */
    @RequestMapping(value = "/check_captcha", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCaptcha(Long captcha, HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("randomCode") != null) {
            int randomCode = (Integer) request.getSession().getAttribute("randomCode");
            return captcha == randomCode ? true : false;
        } else {
            return false;
        }

    }

    /**
     * 注册提交
     */
    @RequestMapping(value = "/submit_email", method = RequestMethod.POST)
    public @ResponseBody
    Message submitEmail(String captchaId, String captcha, String email, Long userId, String registStep, String userImg,
                        String trueName, String idCardImg, HttpServletRequest request, HttpServletResponse response,
                        HttpSession session, ModelMap model) {
        Setting setting = SettingUtils.get();
        Member member = new Member();
        if (userId != null) {// id不为空表示是修改的。
            member = memberService.find(userId);
            if ("2".equals(registStep)) {// 注册属性的值
                member.setUserImg(userImg);
                List<MemberAttribute> memberAttributes = memberAttributeService.findList();
                for (MemberAttribute memberAttribute : memberAttributes) {
                    String parameter = request.getParameter("memberAttribute_" + memberAttribute.getId());
                    if (memberAttribute.getType() == Type.name || memberAttribute.getType() == Type.address
                        || memberAttribute.getType() == Type.zipCode || memberAttribute.getType() == Type.phone
                        || memberAttribute.getType() == Type.mobile || memberAttribute.getType() == Type.text
                        || memberAttribute.getType() == Type.select) {
                        if (memberAttribute.getIsRequired() && StringUtils.isEmpty(parameter)) {
                            return Message.error("shop.common.invalid");
                        }
                        member.setAttributeValue(memberAttribute, parameter);
                    } else if (memberAttribute.getType() == Type.gender) {
                        Gender gender = StringUtils.isNotEmpty(parameter) ? Gender.valueOf(parameter) : null;
                        if (memberAttribute.getIsRequired() && gender == null) {
                            return Message.error("shop.common.invalid");
                        }
                        member.setGender(gender);
                    } else if (memberAttribute.getType() == Type.birth) {
                        try {
                            Date birth = StringUtils.isNotEmpty(parameter) ? DateUtils.parseDate(parameter,
                                                                                                 CommonAttributes.DATE_PATTERNS) : null;
                            if (memberAttribute.getIsRequired() && birth == null) {
                                return Message.error("shop.common.invalid");
                            }
                            member.setBirth(birth);
                        } catch (ParseException e) {
                            return Message.error("shop.common.invalid");
                        }
                    } else if (memberAttribute.getType() == Type.area) {
                        Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
                        if (area != null) {
                            member.setArea(area);
                        } else if (memberAttribute.getIsRequired()) {
                            return Message.error("shop.common.invalid");
                        }
                    } else if (memberAttribute.getType() == Type.checkbox) {
                        String[] parameterValues = request.getParameterValues("memberAttribute_"
                                                                              + memberAttribute.getId());
                        List<String> options = parameterValues != null ? Arrays.asList(parameterValues) : null;
                        if (memberAttribute.getIsRequired() && (options == null || options.isEmpty())) {
                            return Message.error("shop.common.invalid");
                        }
                        member.setAttributeValue(memberAttribute, options);
                    }
                }
                net.shopxx.Template activateAccountMailTemplate = templateService.get("activateAccount");
                SafeKey safeKey = new SafeKey();
                safeKey.setValue(UUID.randomUUID().toString()
                                 + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
                safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(),
                                                                                             setting.getSafeKeyExpiryTime()) : null);
                member.setSafeKey(safeKey);
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("member", member);
                param.put("domain", setting.getSiteUrl());// 以http开头

                mailService.send(member.getEmail(), "帐号激活通知！", activateAccountMailTemplate.getTemplatePath(), param);

            } else if ("3".equals(registStep)) {
                member.setTrueName(trueName);
                member.setIdCardImg(idCardImg);
            }

            member.setRegistStep(registStep);// 注册步数，用来存储当前到第几步了。
            memberService.update(member);
        } else {// id为空表示是新增的。
            String password = rsaService.decryptParameter("enPassword", request);
            rsaService.removePrivateKey(request);

            if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
                return Message.error("shop.captcha.invalid");
            }

            if (!setting.getIsRegisterEnabled()) {
                return Message.error("shop.register.disabled");
            }
            if (!isValid(Member.class, "password", password, Save.class)) {
                return Message.error("shop.common.invalid");
            }
            if (password.length() < setting.getPasswordMinLength()
                || password.length() > setting.getPasswordMaxLength()) {
                return Message.error("shop.common.invalid");
            }
            member.setPassword(DigestUtils.md5Hex(password));
            member.setPoint(setting.getRegisterPoint());
            member.setAmount(new BigDecimal(0));
            member.setBalance(new BigDecimal(0));
            member.setIsEnabled(true);
            member.setIsLocked(false);
            member.setLoginFailureCount(0);
            member.setLockedDate(null);
            member.setRegisterIp(request.getRemoteAddr());
            member.setLoginIp(request.getRemoteAddr());
            member.setLoginDate(new Date());
            member.setSafeKey(null);
            member.setMemberRank(memberRankService.findDefault());
            member.setFavoriteProducts(null);

            member.setUsername(email);// 用户名就是邮箱
            member.setEmail(email);// 邮箱
            member.setRegistStep("1");// 注册步数，用来存储当前到第几步了。

            memberService.save(member);
        }
        Cart cart = cartService.getCurrent();
        if (cart != null && cart.getMember() == null) {
            cartService.merge(member, cart);
            WebUtils.removeCookie(request, response, Cart.ID_COOKIE_NAME);
            WebUtils.removeCookie(request, response, Cart.KEY_COOKIE_NAME);
        }

        Map<String, Object> attributes = new HashMap<String, Object>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Entry<String, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
        member = memberService.find(member.getId());
        // Message message = new Message();
        // message.setType(Message.Type.success);
        // message.setContent("提交成功！");
        // message.setScript(member.getId().toString());
        request.getSession().setAttribute("currentMemberSession", member);
        return Message.success("提交成功！");
    }
}
