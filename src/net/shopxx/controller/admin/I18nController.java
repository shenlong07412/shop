/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.controller.admin;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.BaseEntity.Save;
import net.shopxx.entity.I18n;
import net.shopxx.entity.I18nItem;
import net.shopxx.entity.SystemCodeDetail;
import net.shopxx.service.I18nItemService;
import net.shopxx.service.I18nService;
import net.shopxx.service.SystemCodeDetailService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 国际化代码
 * 
 * @author lianjsh	
 */
@Controller("adminI18nController")
@RequestMapping("/admin/i18n")
public class I18nController extends BaseController {

	@Resource(name = "i18nServiceImpl")
	private I18nService i18nService;
	
	@Resource(name = "i18nItemServiceImpl")
	private I18nItemService i18nItemService;
	
	@Resource(name = "systemCodeDetailServiceImpl")
    private SystemCodeDetailService        systemCodeDetailService;

	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping(value = "/checkCodeExists", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkCodeExists(String  code) {
		if (i18nService.i18nCodeExists(code)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<SystemCodeDetail> i18ncodes = systemCodeDetailService.getListBySystemCode("i18ncode");
		model.addAttribute("i18ncodes",i18ncodes);
		return "/admin/I18n/addI18n";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(I18n i18n,String local,String memo,String zh_CN,String zh_TW,String en_US,RedirectAttributes redirectAttributes) {
		if (!isValid(i18n, Save.class)) {
			return ERROR_VIEW;
		}
		if (i18nService.i18nCodeExists(i18n.getCode())) {
			return ERROR_VIEW;
		}
		i18nService.save(i18n);
		Map<String, I18nItem> map=new HashMap<String, I18nItem>();
		if(local!=null&&!"".equals(local)&&(memo==null||"".equals(memo)))
		{
			if("en_US".equals(local))
			{
				i18n.setMemo(en_US);
			}else if("zh_TW".equals(local))
			{
				i18n.setMemo(zh_TW);
			}else
			{
				i18n.setMemo(zh_CN);
			}
			
			
		}else if(memo!=null&&!"".equals(memo))
		{
			i18n.setMemo(memo);
		}
		if(zh_CN!=null&&!"".equals(zh_CN))
		{
			I18nItem zhcnItem=new I18nItem();
			zhcnItem.setMsg(zh_CN);
			zhcnItem.setLocale("zh_CN");
			zhcnItem.setI18n(i18n);
			i18nItemService.save(zhcnItem);
			map.put("zh_CN", zhcnItem);
			System.out.println("zhcnItem:################"+zhcnItem.getId());
		}
		if(zh_TW!=null&&!"".equals(zh_TW))
		{
			I18nItem zhtwItem=new  I18nItem();
			zhtwItem.setMsg(zh_TW);
			zhtwItem.setLocale("zh_TW");
			zhtwItem.setI18n(i18n);
			i18nItemService.save(zhtwItem);
			map.put("zh_TW", zhtwItem);
			System.out.println("zhtwItem:################"+zhtwItem.getId());
		}
		if(en_US!=null&&!"".equals(en_US))
		{
			I18nItem enusItem=new I18nItem();
			enusItem.setMsg(en_US);
			enusItem.setLocale("en_US");
			enusItem.setI18n(i18n);
			i18nItemService.save(enusItem);
			map.put("en_US", enusItem);
			System.out.println("enusItemId:################"+enusItem.getId());
		}
		i18n.setMsg(map);
		i18nService.update(i18n);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		List<SystemCodeDetail> i18ncode = systemCodeDetailService.getListBySystemCode("i18ncode");
		I18n i18n=i18nService.find(id);
		model.addAttribute("i18ncodes",i18ncode);
		model.addAttribute("i18n",i18n);
		System.out.println("i18n's key =========================="+i18n.getMsg().keySet().toString());
		System.out.println("i18n's key =========================="+i18n.getMsg().values().toString());
		return "/admin/I18n/editI18n";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(I18n i18n,String local,Long zh_CNid,Long zh_TWid,Long en_USid,String memo,String zh_CN,String zh_TW,String en_US,ModelMap model, RedirectAttributes redirectAttributes) {
		if (!isValid(i18n)) {
			return ERROR_VIEW;
		}
		Map<String, I18nItem> map=i18n.getMsg();
		if(local!=null&&!"".equals(local)&&(memo==null||"".equals(memo)))
		{
			if("en_US".equals(local))
			{
				i18n.setMemo(en_US);
			}else if("zh_TW".equals(local))
			{
				i18n.setMemo(zh_TW);
			}else
			{
				i18n.setMemo(zh_CN);
			}
			
			
		}else if(memo!=null&&!"".equals(memo))
		{
			i18n.setMemo(memo);
		}
		if(zh_CN!=null&&!"".equals(zh_CN))
		{
			I18nItem zhcnItem=i18nItemService.find(zh_CNid);
			zhcnItem.setMsg(zh_CN);
			zhcnItem.setLocale("zh_CN");
			zhcnItem.setI18n(i18n);
			i18nItemService.update(zhcnItem);
			map.put("zh_CN", zhcnItem);
		}
		if(zh_TW!=null&&!"".equals(zh_TW))
		{
			I18nItem zhtwItem=i18nItemService.find(zh_TWid);
			zhtwItem.setMsg(zh_TW);
			zhtwItem.setLocale("zh_TW");
			zhtwItem.setI18n(i18n);
			i18nItemService.update(zhtwItem);
			map.put("zh_TW", zhtwItem);
		}
		if(en_US!=null&&!"".equals(en_US))
		{
			I18nItem enusItem=i18nItemService.find(en_USid);
			enusItem.setMsg(en_US);
			enusItem.setLocale("en_US");
			enusItem.setI18n(i18n);
			i18nItemService.update(enusItem);
			map.put("en_US", enusItem);
		}
		i18n.setMsg(map);
		i18nService.update(i18n);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", i18nService.findPage(pageable));
		return "/admin/I18n/listI18n";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= i18nService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		i18nService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}