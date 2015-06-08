package net.shopxx.controller.admin.biz;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.controller.admin.BaseController;
import net.shopxx.entity.BaseEntity.Save;
import net.shopxx.entity.Community;
import net.shopxx.entity.ProductCategory;
import net.shopxx.entity.ProductMerchantCategory;
import net.shopxx.entity.SystemCodeDetail;
import net.shopxx.entity.biz.Merchant;
import net.shopxx.service.CommunityService;
import net.shopxx.service.MerchantService;
import net.shopxx.service.ProductMerchantCategoryService;
import net.shopxx.service.SystemCodeDetailService;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Controller - 商户
 * 
 * @author lianjsh
 */
@Controller("adminMerchantController")
@RequestMapping("/admin/merchant")
public class MerchantController extends BaseController {

    @Resource(name = "merchantServiceImpl")
    private MerchantService                merchantService;

    @Resource(name = "productMerchantCategoryServiceImpl")
    private ProductMerchantCategoryService productMerchantCategoryService;

    @Resource(name = "systemCodeDetailServiceImpl")
    private SystemCodeDetailService        systemCodeDetailService;

    @Resource(name = "communityServiceImpl")
    private CommunityService               communityService;

    /**
     * 检查用户名是否存在
     */
    @RequestMapping(value = "/check_name", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String name) {
        if (merchantService.usernameExists(name)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, ModelMap model) {
        Merchant merchant = merchantService.find(id);
        model.addAttribute("merchant", merchant);
        JSONArray ja = new JSONArray();
        if (merchant != null) {
            List<ProductMerchantCategory> list = productMerchantCategoryService.findListByMerchantId(merchant.getId());
            if (list != null && list.size() != 0) {
                for (ProductMerchantCategory pmc : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", pmc.getProductCategory().getId());
                    jo.put("status", pmc.getStatus());
                    ja.add(jo);
                }
            }
        }
        model.put("jsonStr", ja.toJSONString());
        return "/admin/merchant/viewMerchant";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
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
        return "/admin/merchant/addMerchant";
    }

    /**
     * 保存商户
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Merchant merchant, Long areaId, String cids, RedirectAttributes redirectAttributes,
                       ModelMap model) {
        if (!isValid(merchant, Save.class)) {
            return ERROR_VIEW;
        }
        if (merchantService.usernameExists(merchant.getName())) {
            return ERROR_VIEW;
        }
        merchant.getAccount().setMerchant(merchant);
        merchant.getEnterprise().setMerchant(merchant);
        merchant.getLegalPerson().setMerchant(merchant);
        merchant.getBankAccount().setMerchant(merchant);
        Community community = communityService.find(areaId);
        merchant.setCommunity(community);

        merchantService.save(merchant);

        // 保存授权信息

        if (cids == null || cids.length() == 0) {
            // 全删除
            productMerchantCategoryService.deleteByMerchantId(merchant.getId());
        } else {
            // 先删除 再添加
            productMerchantCategoryService.deleteByMerchantId(merchant.getId());
            JSONArray jsonArray = JSONArray.parseArray(cids);
            for (int i = 0; i < jsonArray.size(); i++) {
                ProductMerchantCategory productMerchantCategory = new ProductMerchantCategory();
                productMerchantCategory.setMerchant(merchant);
                ProductCategory productCategory = new ProductCategory();
                JSONObject jo = jsonArray.getJSONObject(i);
                Long treeId = jo.getLong("treeId");
                productCategory.setId(treeId);
                productMerchantCategory.setProductCategory(productCategory);
                if (jo.getBoolean("isParent")) {
                    Boolean isHalfCheck = jo.getBoolean("halfCheck");
                    if (isHalfCheck) {
                        productMerchantCategory.setStatus(2L);// 数据为半选
                    } else {
                        productMerchantCategory.setStatus(1L);// 数据为全选
                    }
                } else {
                    productMerchantCategory.setStatus(1l);// 数据为全选
                }
                productMerchantCategoryService.save(productMerchantCategory);
            }
        }

        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        Merchant merchant = merchantService.find(id);
        model.addAttribute("merchant", merchant);
        JSONArray ja = new JSONArray();
        if (merchant != null) {
            List<ProductMerchantCategory> list = productMerchantCategoryService.findListByMerchantId(merchant.getId());
            if (list != null && list.size() != 0) {
                for (ProductMerchantCategory pmc : list) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", pmc.getProductCategory().getId());
                    jo.put("status", pmc.getStatus());
                    ja.add(jo);
                }
            }
        }
        model.put("jsonStr", ja.toJSONString());

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
        model.addAttribute("merchant", merchant);
        model.addAttribute("channelId", channelId);
        model.addAttribute("templateId", templateId);

        return "/admin/merchant/editMerchant";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Merchant merchant, Long id, Long areaId, String cids, RedirectAttributes redirectAttributes,
                         ModelMap model) {
        Merchant old = merchantService.find(id);
        if (!isValid(merchant)) {
            return ERROR_VIEW;
        }
        BeanUtils.copyProperties(old.getAccount(), merchant.getAccount(), new String[] { "username", "realname",
                "nickname", "status", "createDate", "modifyDate", "dataflg", "createby", "updateby" });
        BeanUtils.copyProperties(old.getEnterprise(), merchant.getEnterprise(), new String[] { "busRoute", "city" });
        merchant.getLegalPerson().setMerchant(merchant);
        merchant.getBankAccount().setMerchant(merchant);

        // 保存商家所在的社区信息
        Community community = communityService.find(areaId);
        merchant.setCommunity(community);
        community.getMerchant().add(merchant);
        merchantService.update(merchant);

        // 保存授权信息

        if (cids == null || cids.length() == 0) {
            // 全删除
            productMerchantCategoryService.deleteByMerchantId(merchant.getId());
        } else {
            // 先删除 再添加
            productMerchantCategoryService.deleteByMerchantId(merchant.getId());
            JSONArray jsonArray = JSONArray.parseArray(cids);
            for (int i = 0; i < jsonArray.size(); i++) {
                ProductMerchantCategory productMerchantCategory = new ProductMerchantCategory();
                productMerchantCategory.setMerchant(merchant);
                ProductCategory productCategory = new ProductCategory();
                JSONObject jo = jsonArray.getJSONObject(i);
                Long treeId = jo.getLong("treeId");
                productCategory.setId(treeId);
                productMerchantCategory.setProductCategory(productCategory);
                if (jo.getBoolean("isParent")) {
                    Boolean isHalfCheck = jo.getBoolean("halfCheck");
                    if (isHalfCheck) {
                        productMerchantCategory.setStatus(2L);// 数据为半选
                    } else {
                        productMerchantCategory.setStatus(1L);// 数据为全选
                    }
                } else {
                    productMerchantCategory.setStatus(1l);// 数据为全选
                }
                productMerchantCategoryService.save(productMerchantCategory);
            }

        }

        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Long recommend, Long auditSwitch, Pageable pageable, ModelMap model) {
        if (auditSwitch != null && !"".equals(auditSwitch) && auditSwitch != 3) {
            List<Filter> filters = pageable.getFilters();
            Filter filter = new Filter();
            filter.setProperty("auditSwitch");
            filter.setValue(auditSwitch);
            filter.setOperator(Filter.Operator.eq);
            filters.add(filter);
            pageable.setFilters(filters);
        }
        if (recommend != null && !"".equals(recommend) && (recommend == 1 || recommend == 0)) {
            List<Filter> filters = pageable.getFilters();
            Filter filter = new Filter();
            filter.setProperty("recommend");
            filter.setValue(recommend);
            filter.setOperator(Filter.Operator.eq);
            filters.add(filter);
            pageable.setFilters(filters);
        }
        model.addAttribute("page", merchantService.findPage(pageable));
        model.addAttribute("auditSwitch", auditSwitch);
        model.addAttribute("recommend", recommend);
        return "/admin/merchant/listMerchant";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        if (ids.length >= merchantService.count()) {
            return Message.error("admin.common.deleteAllNotAllowed");
        }
        // 先删除相关的菜单权限
        for (Long merchantId : ids) {
            productMerchantCategoryService.deleteByMerchantId(merchantId);
        }

        merchantService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 审核
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public @ResponseBody
    Message check(Long[] ids, Long checkStatus) {
        try {
            for (int i = 0; i < ids.length; i++) {
                Merchant mer = merchantService.find(ids[i]);
                mer.setAuditSwitch(checkStatus);
                merchantService.update(mer);
            }
            return SUCCESS_MESSAGE;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_MESSAGE;

        }
    }

    /**
     * 推荐
     */
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public @ResponseBody
    Message recommend(Long[] ids, int recommend) {
        try {
            for (int i = 0; i < ids.length; i++) {
                Merchant mer = merchantService.find(ids[i]);
                mer.setRecommend(recommend);
                merchantService.update(mer);
            }
            return SUCCESS_MESSAGE;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_MESSAGE;
        }
    }

    /**
     * 解冻
     */
    @RequestMapping(value = "/unfreeze", method = RequestMethod.GET)
    public @ResponseBody
    Message unfreeze(Long[] ids, int unfreeze) {
        try {
            for (int i = 0; i < ids.length; i++) {
                Merchant mer = merchantService.find(ids[i]);
                mer.getAccount().setStatus(unfreeze);
                mer.getAccount().setMerchant(mer);
                merchantService.update(mer);
            }
            return SUCCESS_MESSAGE;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_MESSAGE;
        }
    }
    
    /***************************************************************************************************************/

    /**
     * 注册 
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        return "/admin/merchant/register";
    }
    
    /**
     * 商户认证首页
     */
    @RequestMapping(value = "/certification_index", method = RequestMethod.GET)
    public String certificationIndex(ModelMap model) {
        
        return "/admin/merchant_certification/index";
    }
    /**
     * 账号信息认证
     */
    @RequestMapping(value = "/certification_account", method = RequestMethod.GET)
    public String certificationAccount(ModelMap model) {
        
        return "/admin/merchant_certification/account";
    }
    /**
     * 账号信息认证提交
     */
    @RequestMapping(value = "/certification_account_submit", method = RequestMethod.POST)
    public String certificationAccountSubmit(ModelMap model) {
        
        return "/admin/merchant_certification/account";
    }
    /**
     * 商户信息认证
     */
    @RequestMapping(value = "/certification_merchant", method = RequestMethod.GET)
    public String certificationMerchant(ModelMap model) {
        
        return "/admin/merchant_certification/merchant";
    }
    /**
     * 商户信息提交
     */
    @RequestMapping(value = "/certification_merchant_submit", method = RequestMethod.POST)
    public String certificationMerchantSubmit(ModelMap model) {
        
        return "/admin/merchant_certification/merchant";
    }
    /**
     * 企业基本信息认证
     */
    @RequestMapping(value = "/certification_enterprise", method = RequestMethod.GET)
    public String certificationEnterprise(ModelMap model) {
        
        return "/admin/merchant_certification/enterprise";
    }
    /**
     * 企业基本信息提交
     */
    @RequestMapping(value = "/certification_enterprise_submit", method = RequestMethod.POST)
    public String certificationEnterpriseSubmit(ModelMap model) {
        
        return "/admin/merchant_certification/enterprise";
    }
    
    /**
     * 企业法人信息认证
     */
    @RequestMapping(value = "/certification_leagal_person", method = RequestMethod.GET)
    public String certificationLeagalPerson(ModelMap model) {
        
        return "/admin/merchant_certification/leagal_person";
    }
    /**
     * 企业法人信息提交
     */
    @RequestMapping(value = "/certification_leagal_person_submit", method = RequestMethod.POST)
    public String certificationLeagalPersonSubmit(ModelMap model) {
        
        return "/admin/merchant_certification/leagal_person";
    }
    
    /**
     * 银行账号信息认证
     */
    @RequestMapping(value = "/certification_bank", method = RequestMethod.GET)
    public String certificationBank(ModelMap model) {
        
        return "/admin/merchant_certification/bank";
    }
    /**
     * 银行账号信息认证提交
     */
    @RequestMapping(value = "/certification_bank_submit", method = RequestMethod.POST)
    public String certificationBankSubmit(ModelMap model) {
        
        return "/admin/merchant_certification/bank";
    }
    
    

}
