package net.shopxx.controller.shop.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shopxx.Message;
import net.shopxx.Setting;
import net.shopxx.Setting.CaptchaType;
import net.shopxx.entity.Member;
import net.shopxx.entity.MemberCertification;
import net.shopxx.entity.SafeKey;
import net.shopxx.service.CaptchaService;
import net.shopxx.service.MailService;
import net.shopxx.service.MemberCertificationService;
import net.shopxx.service.MemberService;
import net.shopxx.service.TemplateService;
import net.shopxx.util.SettingUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("shopMemberCertificationController")
@RequestMapping("/member/certification")
public class CertificationController {

    @Resource
    private MemberService              memberService;
    @Resource
    private CaptchaService             captchaService;
    @Resource
    private TemplateService            templateService;
    @Resource
    private MailService                mailService;
    @Resource
    private MemberCertificationService memberCertificationService;

    /**
     * 邮箱认证
     */
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String mail(ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        if (member.getActivateEmail() == null) {
            return "shop/member/certification/mail_1";
        } else if (member.getActivateEmail()) {
            return "shop/member/certification/mail_3";
        } else {
            return "shop/member/certification/mail_2";
        }
    }

    /**
     * 邮箱认证
     */
    @RequestMapping(value = "/mail_submit", method = RequestMethod.POST)
    public @ResponseBody
    Message mailSubmit(String captchaId, String captcha, String email, ModelMap model) {
        Setting setting = SettingUtils.get();
        if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
            return Message.error("shop.captcha.invalid");
        }

        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        model.addAttribute("captchaId", UUID.randomUUID().toString());

        net.shopxx.Template activateAccountMailTemplate = templateService.get("activateAccount");
        SafeKey safeKey = new SafeKey();
        safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
        safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(),
                                                                                     setting.getSafeKeyExpiryTime()) : null);
        member.setSafeKey(safeKey);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("member", member);
        param.put("domain", setting.getSiteUrl());// 以http开头
        mailService.send(email, "帐号激活通知！", activateAccountMailTemplate.getTemplatePath(), param);

        member.setActivateEmail(false);
        memberService.update(member);
        return Message.success("提交成功！");
    }

    /**
     * 手机认证
     */
    @RequestMapping(value = "/mobile", method = RequestMethod.GET)
    public String mobile(ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        if (member.getActivateMobile() != null && member.getActivateMobile()) {
            return "shop/member/certification/mobile_3";
        }
        return "shop/member/certification/mobile_1";
    }

    /**
     * 手机认证2
     */
    @RequestMapping(value = "/mobile2", method = RequestMethod.GET)
    public String mobile2(ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        return "shop/member/certification/mobile_2";
    }

    /**
     * 手机认证，提交
     */
    @RequestMapping(value = "/mobile_submit", method = RequestMethod.POST)
    public @ResponseBody
    Message mobileSubmit(String captchaId, String captcha, String mobile, HttpServletRequest request,
                         HttpServletResponse response, ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
            return Message.error("shop.captcha.invalid");
        }
        if (mobile != null && !"".equals(mobile)) {
            int randomCode = (int) (Math.random() * 900000) + 100000;
            request.getSession().setAttribute("randomCode", randomCode);
            System.out.println("验证码>>>>>>>>>>>>>>>>>>>>" + randomCode);
            return Message.success("提交成功！");
        } else {
            return Message.error("手机号码错误！");
        }

    }

    /**
     * 检查验证码是否正确
     */
    @RequestMapping(value = "/check_captcha", method = RequestMethod.POST)
    public @ResponseBody
    Message checkCaptcha(String mobileCaptcha, HttpServletRequest request, HttpServletResponse response) {
        Member member = memberService.getCurrent();
        if (request.getSession().getAttribute("randomCode") != null) {
            String randomCode = request.getSession().getAttribute("randomCode").toString();
            if (mobileCaptcha.equals(randomCode)) {
                member.setActivateMobile(true);
                memberService.update(member);
                return Message.success("提交成功！");
            }
        }
        return Message.error("验证码错误！");
    }

    /**
     * 实名认证
     */
    @RequestMapping(value = "/realname", method = RequestMethod.GET)
    public String realname(ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        if (member.getActivateIdcard() == null) {
            return "shop/member/certification/realname_1";
        } else if (member.getActivateIdcard()) {
            List<MemberCertification> mcList = memberCertificationService.getListByMemberId(member.getId());
            if (mcList != null && mcList.size() > 0) {
                model.addAttribute("memberCertification", mcList.get(0));
            }
            return "shop/member/certification/realname_3";
        } else {
            return "shop/member/certification/realname_2";
        }

    }

    /**
     * 实名认证
     */
    @RequestMapping(value = "/realname_submit", method = RequestMethod.POST)
    public @ResponseBody
    Message realnameSubmit(MemberCertification memberCertification, HttpServletRequest request,
                           HttpServletResponse response, ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("member", member);
        try {
            memberCertificationService.save(memberCertification);
            member.setActivateIdcard(false);
            memberService.update(member);
            return Message.success("提交成功！");
        } catch (Exception e) {
            return Message.error("提交失败！");
        }
    }
}
