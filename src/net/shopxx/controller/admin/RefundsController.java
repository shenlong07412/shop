/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.service.OrderService;
import net.shopxx.service.RefundsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 退款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminRefundsController")
@RequestMapping(value = { "/admin/refunds", "/biz/refunds" }) 
public class RefundsController extends BaseController {

	@Resource(name = "refundsServiceImpl")
	private RefundsService refundsService;
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("refunds", refundsService.find(id));
		return "/admin/refunds/view";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		//获取当前账户、商户、订单信息
		List<Filter> filters=orderService.getFilters();
		pageable.setFilters(filters);		
		model.addAttribute("page", refundsService.findPage(pageable));
		return "/admin/refunds/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		refundsService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}