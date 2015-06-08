/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.shopxx.Message;
import net.shopxx.entity.Area;
import net.shopxx.entity.Community;
import net.shopxx.entity.ZtreeBean;
import net.shopxx.service.AreaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;

/**
 * Controller - 地区
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminAreaController")
@RequestMapping("/admin/area")
public class AreaController extends BaseController {

	@Resource(name = "areaServiceImpl")
	private AreaService areaService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Long parentId, ModelMap model) {
		model.addAttribute("parent", areaService.find(parentId));
		return "/admin/area/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Area area, Long parentId, RedirectAttributes redirectAttributes) {
		area.setParent(areaService.find(parentId));
		if (!isValid(area)) {
			return ERROR_VIEW;
		}
		area.setFullName(null);
		area.setTreePath(null);
		area.setChildren(null);
		area.setMembers(null);
		area.setReceivers(null);
		area.setOrders(null);
		area.setDeliveryCenters(null);
		areaService.save(area);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("area", areaService.find(id));
		return "/admin/area/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Area area, RedirectAttributes redirectAttributes) {
		if (!isValid(area)) {
			return ERROR_VIEW;
		}
		areaService.update(area, "fullName", "treePath", "parent", "children", "members", "receivers", "orders", "deliveryCenters");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Long parentId, ModelMap model) {
		Area parent = areaService.find(parentId);
		if (parent != null) {
			model.addAttribute("parent", parent);
			model.addAttribute("areas", new ArrayList<Area>(parent.getChildren()));
		} else {
			model.addAttribute("areas", areaService.findRoots());
		}
		return "/admin/area/list";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/listAjax", method = RequestMethod.GET)
	public @ResponseBody
	void listAjax(HttpServletResponse response,Long treeId, ModelMap model,String hasCommunity) {
		Area parent = areaService.find(treeId);
		List<Area> areaList=new ArrayList<Area>();
		if(hasCommunity==null||hasCommunity.equals("")){
			hasCommunity="no";
		}		
		if (parent != null) {
			areaList=new ArrayList<Area>(parent.getChildren());
		} else {
			areaList=areaService.findRoots();
		}
		response.setCharacterEncoding("UTF-8");  
        PrintWriter out;
		try {
			out = response.getWriter();
			List<ZtreeBean> list = new ArrayList<ZtreeBean>();  
			if(areaList.size()>0){
				for (int i = 0; i < areaList.size(); i++) {
					Area area= areaList.get(i);
					if(hasCommunity.equals("no")){
						list=setZtreeBean(list,Integer.parseInt(area.getId()+""),Integer.parseInt(treeId+""),area.getName(),area.getChildren().size()>0?true:false,true);
					}else{						
						list=setZtreeBean(list,Integer.parseInt(area.getId()+""),Integer.parseInt(treeId+""),area.getName(),(area.getChildren().size()>0||area.getCommunities().size()>0)?true:false,false);
					}
				}
			}else{
				List<Community> communities=new ArrayList<Community>(parent.getCommunities());
				if(communities.size()>0){
					for (int i = 0; i < communities.size(); i++) {
						Community community= communities.get(i);
						list=setZtreeBean(list,Integer.parseInt(community.getId()+""),Integer.parseInt(treeId+""),community.getName(),false,true);
					}
				}
			}
	        String str = JSON.toJSONString(list);  
	        out.print(str);
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/getAjax", method = RequestMethod.GET)
	public @ResponseBody
	String getAjax(HttpServletResponse response,Long id) {
		Area area = areaService.find(id);
		String str = JSON.toJSONString(area.getFullName());
		return str;
        
	}
	
	
	public List<ZtreeBean> setZtreeBean(List<ZtreeBean> list,Integer treeId,Integer parentId,String name,Boolean isParent,Boolean isClick) {
		ZtreeBean zb=new ZtreeBean();
		if(isClick==null){
			isClick=true;
		}
		zb.setTreeId(treeId);
		zb.setParentId(parentId);
		zb.setName(name);
		zb.setIsParent(isParent);
		zb.setIsClick(isClick);
		list.add(zb);
		return list;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id) {
		areaService.delete(id);
		return SUCCESS_MESSAGE;
	}

}