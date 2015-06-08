/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.BaseEntity.Save;
import net.shopxx.entity.Area;
import net.shopxx.entity.Community;
import net.shopxx.service.AreaService;
import net.shopxx.service.CommunityService;
import net.shopxx.util.PageableUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 社区
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminCommunityController")
@RequestMapping("/admin/community")
public class CommunityController extends BaseController {

	@Resource(name = "communityServiceImpl")
	private CommunityService communityService;
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model,Long areaId) {
		if(areaId!=null&&areaId>0){
			model.addAttribute("area", areaService.find(areaId));
		}
		
		return "/admin/community/add";
	}

	/**
	 * 保存社区
	 * 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Community community, long areaId, String name, Double longitude, 
			Double latitude,Double radius,String address,String introduction,
			RedirectAttributes redirectAttributes,String recruitMap) {
		community.setArea(areaService.find(areaId));
		if (!isValid(community, Save.class)) {
			return ERROR_VIEW;
		}
		if(recruitMap!=null){
			community.setJson(recruitMap);
		}
		
		community.setName(name);
		community.setLongitude(longitude);
		community.setLatitude(latitude);
		community.setRadius(radius);
		community.setAddress(address);
		community.setStatus(1);//待审
		community.setIntroduction(introduction);
		community.setSubmittedDate(new Date());
		communityService.save(community);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		toEditView(id, model);
		return "/admin/community/edit";
	}

	
	/**
	 * 审核详情
	 */
	@RequestMapping(value = "/editAudit", method = RequestMethod.GET)
	public String listAudit(Long id, ModelMap model) {
		toEditView(id, model);
		return "/admin/community/editAudit";
	}

	private void toEditView(Long id, ModelMap model) {
		model.addAttribute("community", communityService.find(id));
		
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(int status,String opinion,Community community, long id,long areaId, String name, Double longitude, 
			Double latitude,Double radius,String address,String introduction,String type,
			RedirectAttributes redirectAttributes,String recruitMap) {
		
		community=communityService.find(id);
		if(areaId>0)
			community.setArea(areaService.find(areaId));
		
		if (!isValid(community)) {
			return ERROR_VIEW;
		}

		if(recruitMap!=null&&!name.equals("")){
			community.setJson(recruitMap);
		}
		
		if(name!=null&&!name.equals(""))
			community.setName(name);
		
		if(longitude!=null&&!longitude.equals(""))
			community.setLongitude(longitude);
		
		if(latitude!=null&&!latitude.equals(""))
			community.setLatitude(latitude);
		
		if(radius!=null&&!radius.equals(""))
			community.setRadius(radius);

		if(address!=null&&!address.equals(""))
			community.setAddress(address);
		
		if(introduction!=null&&!introduction.equals(""))
			community.setIntroduction(introduction);
		
		if(status>0){
			community.setStatus(status);
			community.setAuditedDate(new Date());
		}
		
		if(opinion!=null&&!opinion.equals(""))
			community.setOpinion(opinion);
		
		communityService.update(community);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		if(type!=null&&type.equals("Audit")){
			return "redirect:listAudit.jhtml";
		}
		
		return "redirect:list.jhtml";
	}

	/**
	 * 审核
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	Message check(int status,Long[] ids) {
		try {
			for (int i = 0; i < ids.length;i++) {		
				Community community= communityService.find(ids[i]);
				community.setStatus(status);
				communityService.update(community);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR_MESSAGE;
			
		}
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model,Long areaId) {
		if(areaId!=null&&areaId>0){
			Area area=new Area();
			area.setId(areaId);			
			PageableUtils.setFilter(pageable,Filter.Operator.eq, "area", area);			
			model.addAttribute("areaId", areaId);			
		}
		
		model.addAttribute("page", communityService.findPage(pageable));
		return "/admin/community/list";
	}
	
	/**
	 * 审核列表
	 */
	@RequestMapping(value = "/listAudit", method = RequestMethod.GET)
	public String listAudit(Pageable pageable, ModelMap model,Long areaId) {
		if(areaId!=null&&areaId>0){
			List<Filter> filters= pageable.getFilters();
			Filter filter=new Filter();
			Area area=new Area();
			area.setId(areaId);			
			filter.setProperty("area");
			filter.setValue(area);
			filter.setOperator(Filter.Operator.eq);
			filters.add(filter);
			pageable.setFilters(filters);
			model.addAttribute("areaId", areaId);
			
		}
		model.addAttribute("page", communityService.findPage(pageable));
		return "/admin/community/listAudit";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= communityService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		communityService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 获取社区树，地区数，
	 * hasCommunity yes：要社区一级；no 不查找社区一级
	 * methodString=请求方法  例如 listAudit.jhtml
	 * IdName:接收的社区id名  默认 id  ;
	 * 
	 * 例子 传入 IdName=commnId;methodString=listAudit.jhtml
	 * 
	 * iframe url =listAudit.jhtml?commnId="获取社区id"
	 */
	@RequestMapping(value = "/communityMain", method = RequestMethod.GET)
	public String main(Pageable pageable, ModelMap model,String hasCommunity,String methodString,String IdName) {
		model.addAttribute("methodString",methodString);
		model.addAttribute("hasCommunity",hasCommunity);
		model.addAttribute("IdName",IdName);
		return "/admin/community/main";
	}
	
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public String tree(ModelMap model,String hasCommunity,String methodString,String IdName,String left) {
		model.addAttribute("methodString",methodString);
		model.addAttribute("hasCommunity",hasCommunity);
		model.addAttribute("IdName",IdName);
		model.addAttribute("left",left);
		return "/admin/community/tree";
	}
}