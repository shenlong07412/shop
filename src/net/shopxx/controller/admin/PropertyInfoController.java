/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.admin;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Community;
import net.shopxx.entity.PropertyInfo;
import net.shopxx.entity.Propertytocommunity;
import net.shopxx.service.CommunityService;
import net.shopxx.service.PropertyInfoService;
import net.shopxx.service.PropertytocommunityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 

/**
 * Controller -  物业信息
 * 
 * @author fangym
 * @version 1.0
 */
@Controller("adminPropertyInfoController")
@RequestMapping("/admin/propertyInfo")
public class PropertyInfoController extends BaseController {

	@Resource(name = "propertyInfoServiceImpl")
	private PropertyInfoService propertyInfoService;

    @Resource(name = "communityServiceImpl")
    private CommunityService communityService;
	
    @Resource(name= "propertytocommunityServiceImpl")
    private PropertytocommunityService propertytocommunityService; 
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
	 
		return "/admin/propertyInfo/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(PropertyInfo propertyInfo,String[] communityIds,String[] _headPerson, RedirectAttributes redirectAttributes) {
		if (!isValid(propertyInfo)) {
			return ERROR_VIEW;
		}
		propertyInfoService.save(propertyInfo);
		if(communityIds!=null){
             for (int i = 0; i < communityIds.length; i++) {
                 if(communityIds[i]==null){
                     continue;
                 }
                 Community community  = communityService.find(Long.parseLong(communityIds[i]));
                 if(community!=null){
                     Propertytocommunity propertytocommunity = new Propertytocommunity();
                     PropertyInfo propertyInfoTmp = new PropertyInfo();
                     propertyInfoTmp.setId(propertyInfo.getId());
                     propertytocommunity.setHeadPerson(_headPerson[i]);
                     propertytocommunity.setCommunity(community);
                     propertytocommunity.setPropertyInfo(propertyInfo);
                     propertytocommunityService.save(propertytocommunity);
                 }
                 
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
		model.addAttribute("propertyInfo", propertyInfoService.find(id));
		model.addAttribute("list", propertytocommunityService.findByPropertyId(id));
		return "/admin/propertyInfo/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(PropertyInfo propertyInfo, String[] communityIds,String[] _headPerson, RedirectAttributes redirectAttributes) {

		if (!isValid(propertyInfo)) {
			return ERROR_VIEW;
		}
 
		propertyInfoService.update(propertyInfo);
		propertytocommunityService.deleteByPropertyId(propertyInfo.getId());
		if(communityIds!=null){
		    for (int i = 0; i < communityIds.length; i++) {
                if(communityIds[i]==null){
                    continue;
                }
                Community community  = communityService.find(Long.parseLong(communityIds[i]));
                if(community!=null){
                    Propertytocommunity propertytocommunity = new Propertytocommunity();
                    PropertyInfo propertyInfoTmp = new PropertyInfo();
                    propertyInfoTmp.setId(propertyInfo.getId());
                    propertytocommunity.setHeadPerson(_headPerson[i]);
                    propertytocommunity.setCommunity(community);
                    propertytocommunity.setPropertyInfo(propertyInfo);
                    propertytocommunityService.save(propertytocommunity);
                }
                
           }
		}
		
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", propertyInfoService.findPage(pageable));
		return "/admin/propertyInfo/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long[] ids) {
	    propertyInfoService.delete(ids);
	    for (int i = 0; i < ids.length; i++) {
	        propertytocommunityService.deleteByPropertyId(ids[i]);
        }
		return SUCCESS_MESSAGE;
	}

}