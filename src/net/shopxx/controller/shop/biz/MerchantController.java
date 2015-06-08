package net.shopxx.controller.shop.biz;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.shopxx.Message;
import net.shopxx.Setting;
import net.shopxx.Setting.CaptchaType;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Community;
import net.shopxx.entity.SafeKey;
import net.shopxx.entity.SystemCodeDetail;
import net.shopxx.entity.biz.Account;
import net.shopxx.entity.biz.Merchant;
import net.shopxx.service.AccountService;
import net.shopxx.service.CaptchaService;
import net.shopxx.service.CommunityService;
import net.shopxx.service.MailService;
import net.shopxx.service.MerchantService;
import net.shopxx.service.SystemCodeDetailService;
import net.shopxx.service.TemplateService;
import net.shopxx.util.SettingUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("shopMerchantController")
@RequestMapping("/shop/merchant")
public class MerchantController extends BaseController {

    @Resource
    private MerchantService         merchantService;
    @Resource
    private AccountService          accountService;
    @Resource
    private SystemCodeDetailService systemCodeDetailService;
    @Resource
    private CaptchaService          captchaService;
    @Resource
    private CommunityService        communityService;
    @Resource
    private MailService             mailService;
    @Resource
    private TemplateService         templateService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regist(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<SystemCodeDetail> accountStatus = systemCodeDetailService.getListBySystemCode("accountStatus");
        List<SystemCodeDetail> profit = systemCodeDetailService.getListBySystemCode("profit");
        List<SystemCodeDetail> billingType = systemCodeDetailService.getListBySystemCode("billingType");
        List<SystemCodeDetail> supportedRebate = systemCodeDetailService.getListBySystemCode("supportedRebate");
        List<SystemCodeDetail> auditSwitch = systemCodeDetailService.getListBySystemCode("auditSwitch");
        List<SystemCodeDetail> channelId = systemCodeDetailService.getListBySystemCode("channelId");
        List<SystemCodeDetail> templateId = systemCodeDetailService.getListBySystemCode("templateId");

        model.addAttribute("accountStatus", accountStatus);
        model.addAttribute("profit", profit);
        model.addAttribute("billingType", billingType);
        model.addAttribute("supportedRebate", supportedRebate);
        model.addAttribute("auditSwitch", auditSwitch);
        model.addAttribute("channelId", channelId);
        model.addAttribute("templateId", templateId);

        model.addAttribute("captchaId", UUID.randomUUID().toString());

        if (request.getSession().getAttribute("merchant_register_step") != null) {
            return "/shop/merchant/register_2";
        }

        return "/shop/merchant/register";
    }

    /**
     * 注册提交
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Message submitEmail(String captchaId, String captcha, String communityId, Long channelId, String username,
                        String password, HttpServletRequest request, HttpServletResponse response, HttpSession session,
                        ModelMap model) {
        Setting setting = SettingUtils.get();
        if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
            return Message.error("shop.captcha.invalid");
        }

        String[] communityIdArr = communityId.split(";");
        String communityIdStr = communityIdArr[1];
        Community community = communityService.find(Long.parseLong(communityIdStr));

        Merchant merchant = new Merchant();
        merchant.setChannelId(channelId);
        merchant.setCommunity(community);
        merchantService.save(merchant);

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setMerchant(merchant);
        accountService.save(account);

        SafeKey safeKey = new SafeKey();
        safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
        safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(),
                                                                                     setting.getSafeKeyExpiryTime()) : null);
        // account.setSafeKey(safeKey);保存秘钥信息

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", account);
        param.put("merchant", merchant);
        param.put("domain", setting.getSiteUrl());// 以http开头
        net.shopxx.Template activateAccountMailTemplate = templateService.get("activateAccount");
        mailService.send(account.getUsername(), "帐号激活通知！", activateAccountMailTemplate.getTemplatePath(), param);// 发送邮件

        try {
            request.getSession().removeAttribute("merchant_register_step");
            request.getSession().setAttribute("merchant_register_step", "2");
        } catch (Exception e) {
            // TODO: handle exception
        }

        return Message.success("提交成功！");
    }

    // /**
    // * 注册提交
    // */
    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    // public @ResponseBody
    // Message submitEmail(String captchaId, String captcha, Account account, BankAccount bankAccount,
    // Enterprise enterprise, LegalPerson legalPerson, Merchant merchant, HttpServletRequest request,
    // HttpServletResponse response, HttpSession session, ModelMap model) {
    // if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
    // return Message.error("shop.captcha.invalid");
    // }
    //
    // JSONObject dataJson = JSONObject.parseObject(request.getParameter("data"));
    // Map<String, Object> dataMap = dataJson;
    // if (dataMap.get("communityId") != null) {
    // dataMap.put("address", dataMap.get("communityId").toString());
    // }
    //
    // account = mapConvertBean(Account.class, dataMap);// 账号信息，通过
    // if (dataMap.get("auditSwitch") != null) {
    // dataMap.put("auditSwitch", Long.parseLong((String) dataMap.get("auditSwitch")));
    // }
    // if (dataMap.get("channelId") != null) {
    // dataMap.put("channelId", Long.parseLong((String) dataMap.get("channelId")));
    // }
    // if (dataMap.get("billingType") != null) {
    // dataMap.put("billingType", Long.parseLong((String) dataMap.get("billingType")));
    // }
    // if (dataMap.get("billingCycle") != null) {
    // dataMap.put("billingCycle", Long.parseLong((String) dataMap.get("billingCycle")));
    // }
    // if (dataMap.get("supportedRebate") != null) {
    // dataMap.put("supportedRebate", Long.parseLong((String) dataMap.get("supportedRebate")));
    // }
    // if (dataMap.get("templateId") != null) {
    // dataMap.put("templateId", Long.parseLong((String) dataMap.get("templateId")));
    // }
    // if (dataMap.get("profit") != null) {
    // dataMap.put("profit", Long.parseLong((String) dataMap.get("profit")));
    // }
    // if (dataMap.get("royaltyRate") != null) {
    // dataMap.put("royaltyRate", Double.parseDouble((String) dataMap.get("royaltyRate")));
    // }
    // if (dataMap.get("templateType") != null) {
    // dataMap.put("templateId", Long.parseLong((String) dataMap.get("templateType")));
    // }
    // merchant = mapConvertBean(Merchant.class, dataMap);// 商户信息
    //
    // try {
    // merchantService.save(merchant);
    // account.setMerchant(merchant);
    // accountService.save(account);
    // request.getSession().removeAttribute("merchant_register_step");
    // request.getSession().setAttribute("merchant_register_step", "2");
    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    //
    // enterprise = mapConvertBean(Enterprise.class, dataMap);// 企业基本信息，通过
    //
    // legalPerson = mapConvertBean(LegalPerson.class, dataMap);// 企业法人信息，通过
    // if (dataMap.get("legalPersonRealname") != null) {
    // legalPerson.setRealname(dataMap.get("legalPersonRealname").toString());
    // }
    // if (dataMap.get("legalPersonPhone") != null) {
    // legalPerson.setPhone(dataMap.get("legalPersonPhone").toString());
    // }
    // if (dataMap.get("legalPersonAddress") != null) {
    // legalPerson.setAddress(dataMap.get("legalPersonAddress").toString());
    // }
    // bankAccount = mapConvertBean(BankAccount.class, dataMap);// 银行账号信息， 通过
    //
    // return Message.success("提交成功！");
    // }

    /**
     * 检查用户名是否被禁用或已存在
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        } else {
            return !accountService.usernameExists(username);
        }
    }

    /**
     * 检查该社区是否存在
     */
    @RequestMapping(value = "/check_community", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCommunity(String communityId) {
        if (communityId != null) {
            String[] cisArray = communityId.split(";");
            if (cisArray != null && cisArray.length > 0 && "2".equals(cisArray[0])) {
                return true;
            }
        }
        return false;
    }

    public static <T> T mapConvertBean(Class<T> type, Map<String, Object> map) {
        T t = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            // 获取类属性 　
            t = type.newInstance();
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                String propertyName = descriptor.getName();
                System.out.print("key=" + propertyName);
                if (map.containsKey(propertyName)) {
                    System.out.println("，value=" + map.get(propertyName));
                    if (descriptor.getPropertyType() == map.get(propertyName).getClass()) {
                        descriptor.getWriteMethod().invoke(t, map.get(propertyName));
                    } else {
                        System.out.println("类型不匹配！");
                    }

                } else {
                    System.out.println("，value=");
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return t;
    }
}
