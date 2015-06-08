/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Menu;
import net.shopxx.entity.Role;
import net.shopxx.service.MenuService;
import net.shopxx.service.RoleService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import freemarker.template.utility.StringUtil;

/**
 * Controller - 角色
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminRoleController")
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	@Resource(name="menuServiceImpl")
	private MenuService menuService;
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add( ModelMap model) {
	       model.addAttribute("menuList", menuService.findRootMenuList());
		return "/admin/role/add";
	}

	/**
     * 添加详细编码
     */
    @RequestMapping(value = "/addDetail")
    public String addDetail(Role role, ModelMap model, RedirectAttributes redirectAttributes) {
        if (!isValid(role)) {
            return ERROR_VIEW;
        }

        model.addAttribute("role",role);
        List<Menu> menuList = menuService.findRootMenuList();
        model.addAttribute("menuList", menuList);

        //取出所有的菜单
        List<Menu> detailMenuList = new ArrayList<Menu>();
        List<Menu> menuTree = menuService.findTree();
        Map<Long,List<String>> detailCodesMap = new HashMap<Long,List<String>>();
        for (Menu menu : menuTree) {
            if(role.getAuthorities().contains(menu.getCode())){
                
                List<String> codeDtailList = new ArrayList<String>();
                if(!StringUtils.isBlank(menu.getDetailCode())){
                    String[] tmp = menu.getDetailCode().split(",");
                    for (int i = 0; i < tmp.length; i++) {
                        codeDtailList.add(tmp[i]);
                    }
                }
                if(codeDtailList.size()!=0){
                    detailMenuList.add(menu);
                    detailCodesMap.put(menu.getId(), codeDtailList);
                }
               
            }
        }
        model.addAttribute("detailMenuList", detailMenuList);
        model.addAttribute("detailCodesMap", detailCodesMap);
        
 
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "/admin/role/addDetail";
    }
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Role role, RedirectAttributes redirectAttributes) {
		if (!isValid(role)) {
			return ERROR_VIEW;
		}
		role.setIsSystem(false);
		role.setAdmins(null);
		roleService.save(role);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit")
	public String edit(Long id, ModelMap model) {
		model.addAttribute("role", roleService.find(id));
		model.addAttribute("menuList", menuService.findRootMenuList());
		return "/admin/role/edit";
	}

	/**
	 * 编辑详细编码
	 */
	@RequestMapping(value = "/editDetail")
	public String editDetail(Role role, ModelMap model, RedirectAttributes redirectAttributes) {
		if (!isValid(role)) {
			return ERROR_VIEW;
		}
		Role pRole = roleService.find(role.getId());
		if (pRole == null || pRole.getIsSystem()) {
			return ERROR_VIEW;
		}
		model.addAttribute("role",role);
		model.addAttribute("pRole",pRole);
		List<Menu> menuList = menuService.findRootMenuList();
		model.addAttribute("menuList", menuList);

		//取出所有的菜单
		List<Menu> detailMenuList = new ArrayList<Menu>();
		List<Menu> menuTree = menuService.findTree();
		Map<Long,List<String>> detailCodesMap = new HashMap<Long,List<String>>();
	    for (Menu menu : menuTree) {
	        if(role.getAuthorities().contains(menu.getCode())){
	            
	            List<String> codeDtailList = new ArrayList<String>();
	            if(!StringUtils.isBlank(menu.getDetailCode())){
	                String[] tmp = menu.getDetailCode().split(",");
	                for (int i = 0; i < tmp.length; i++) {
	                    codeDtailList.add(tmp[i]);
                    }
	            }
	            if(codeDtailList.size()!=0){
	                detailMenuList.add(menu);
	                detailCodesMap.put(menu.getId(), codeDtailList);
	            }
	           
	        }
	    }
	    model.addAttribute("detailMenuList", detailMenuList);
	    model.addAttribute("detailCodesMap", detailCodesMap);
		
 
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "/admin/role/editDetail";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        if (!isValid(role)) {
            return ERROR_VIEW;
        }
        Role pRole = roleService.find(role.getId());
        if (pRole == null || pRole.getIsSystem()) {
            return ERROR_VIEW;
        }
        roleService.update(role, "isSystem", "admins");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
	
	

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", roleService.findPage(pageable));
		return "/admin/role/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				Role role = roleService.find(id);
				if (role != null && (role.getIsSystem() || (role.getAdmins() != null && !role.getAdmins().isEmpty()))) {
					return Message.error("admin.role.deleteExistNotAllowed", role.getName());
				}
			}
			roleService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

}