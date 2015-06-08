/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Community;
import net.shopxx.entity.ComplaintRep;
import net.shopxx.entity.ComplaintRepAnnex;
import net.shopxx.entity.ComplaintRepLog;
import net.shopxx.service.AdminService;
import net.shopxx.service.ComplaintService;
import net.shopxx.service.SystemCodeDetailService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Controller - 投诉报修
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminComplaintController")
@RequestMapping("/admin/complaint")
public class ComplaintController extends BaseController {

	
	@Resource(name = "systemCodeDetailServiceImpl")
	private SystemCodeDetailService systemCodeDetailService;
	
	@Resource(name = "complaintServiceImpl")
	private ComplaintService complaintService;
	
	//private String REPAIR="baoxiu";
	private String COMPLAINT="tousu";
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	

	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		ComplaintRep complaintRep= complaintService.find(id);
		model.addAttribute("complaint", complaintRep);
		model.addAttribute("logs", new ArrayList<ComplaintRepLog>(complaintRep.getLogs()));
		model.addAttribute("imgs", new ArrayList<ComplaintRepAnnex>(complaintRep.getAnnexs()));
		if(complaintRep.getStatus()!=3){
			model.addAttribute("IsEnd","NO");
		}
		model.addAttribute("COMPLAINT", COMPLAINT);	 
		model.addAttribute("systype",systemCodeDetailService.getByCodeDetail(complaintRep.getType()));
		return "/admin/complaint/edit";
	}


	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(long status,long id,String person,String content,RedirectAttributes redirectAttributes) {		
		ComplaintRep complaintRep= complaintService.find(id);
		ComplaintRepLog repLog=new ComplaintRepLog();
		repLog.setContent(content);
		repLog.setPerson(person);
		repLog.setSatus(status);
		repLog.setCoRep(complaintRep);
		repLog.setHanding_time(new Date());
		complaintService.saveLog(repLog);
		
		//Set<ComplaintRepLog> complaintRepLogs= complaintRep.getLogs();
		//complaintRepLogs.add(repLog);
		
		complaintRep.setStatus(status);
		complaintRep.setHanding(person);
		
		complaintService.update(complaintRep);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit.jhtml?id="+id;
	}

	
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model,String status,Long communityId) {
		List<Filter> filters= pageable.getFilters();
		if(status!=null&&!status.equals("")){			
			Filter filter=new Filter();
			filter.setProperty("status");
			filter.setValue(status);
			filter.setOperator(Filter.Operator.eq);
			filters.add(filter);
			pageable.setFilters(filters);
			model.addAttribute("status", status);
		}
		if(communityId!=null&&communityId>0){			
			Filter filter=new Filter();
			Community community=new Community();
			community.setId(communityId);
			filter.setProperty("community");
			filter.setValue(community);
			filter.setOperator(Filter.Operator.eq);
			filters.add(filter);
			pageable.setFilters(filters);
			model.addAttribute("communityId", communityId);
		}
		//pageable.setSearchProperty("content");
		//systemCodeDetailService.getList("baoxiu");
		model.addAttribute("page", complaintService.findPage(pageable));
		model.addAttribute("COMPLAINT", COMPLAINT);		
		return "/admin/complaint/list";
	}
	
	

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= complaintService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		complaintService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 主页
	 */
	@RequestMapping(value = "/communityMain", method = RequestMethod.GET)
	public String main(Pageable pageable, ModelMap model) {
		//model.addAttribute("page", communityService.findPage(pageable));
		return "/admin/community/main";
	}
}