package net.shopxx.controller.admin;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Community;
import net.shopxx.entity.OwnerInfo;
import net.shopxx.entity.OwnerPaymentLog;
import net.shopxx.service.CommunityService;
import net.shopxx.service.OwnerPaymentLogService;
import net.shopxx.service.OwnerService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 业主
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminOwnerController")
@RequestMapping("/admin/owner")
public class OwnerController extends BaseController{
	
	@Resource(name = "ownerServiceImpl")
	private OwnerService ownerService;
	
	@Resource(name = "ownerPaymentLogServiceImpl")
	private OwnerPaymentLogService ownerPaymentLogService;
	
	@Resource(name = "communityServiceImpl")
	private CommunityService communityService;	
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( Pageable pageable, ModelMap model) {
		if(pageable.getSearchValue()!=null && "".equals(pageable.getSearchValue().trim())){
			pageable.setSearchValue(null);
		}
		Page<OwnerInfo> page =ownerService.findPage(pageable);
		model.addAttribute("page", page);
		return "/admin/owner/list";
	}

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("communityTree", communityService.findListForAll(null,null,null));
        return "/admin/owner/add";
    }	
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save")
    public String save(OwnerInfo ownerInfo,Long areaId,RedirectAttributes redirectAttributes,ModelMap model) {
    	Community community = communityService.find(areaId);
    	ownerInfo.setCommunity(community);
    	ownerInfo.setDataflg(1l);
    	ownerService.save(ownerInfo);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
    }    

	/**
	 * 业主信息编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		OwnerInfo owner =ownerService.find(id);
		model.addAttribute("owner", owner);
		return "/admin/owner/edit";
	}	    

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public String update(OwnerInfo ownerInfo,Long areaId,RedirectAttributes redirectAttributes,ModelMap model) {
    	Community community = communityService.find(areaId);
    	ownerInfo.setCommunity(community);
    	ownerService.update(ownerInfo);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
    } 	
	
	/**
	 * 业主信息查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("owner", ownerService.find(id));
		return "/admin/owner/view";
	}		

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= ownerService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		ownerService.delete(ids);
		return SUCCESS_MESSAGE;
	}	
	
	/**
	 * 日志列表
	 */
	@RequestMapping(value = "/paymentLogList", method = RequestMethod.GET)
	public String paymentLogList( Pageable pageable, ModelMap model) {
		if(pageable.getSearchValue()!=null && "".equals(pageable.getSearchValue().trim())){
			pageable.setSearchValue(null);
		}
		Page<OwnerPaymentLog> page =ownerPaymentLogService.findPage(pageable);
		model.addAttribute("page", page);
		return "/admin/owner/paymentLog/list";
	}
	
	/**
	 * 日志信息查看
	 */
	@RequestMapping(value = "/paymentLogView", method = RequestMethod.GET)
	public String paymentLogView(Long id, ModelMap model) {
		model.addAttribute("paymentLog", ownerPaymentLogService.find(id));
		return "/admin/owner/paymentLog/view";
	}	

	/**
	 * 检查编号是否唯一
	 */
	@RequestMapping(value = "/check_no", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkSn(String previousNo, String no) {
		if (StringUtils.isEmpty(no)) {
			return false;
		}
		if (ownerService.noUnique(previousNo, no)) {
			return true;
		} else {
			return false;
		}
	}	
	
}
