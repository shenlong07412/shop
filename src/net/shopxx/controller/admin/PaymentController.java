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
import net.shopxx.entity.Payment;
import net.shopxx.service.OrderService;
import net.shopxx.service.PaymentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 收款单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminPaymentController")
@RequestMapping(value = { "/admin/payment", "/biz/payment" }) 
public class PaymentController extends BaseController {

	@Resource(name = "paymentServiceImpl")
	private PaymentService paymentService;
	@Resource(name = "orderServiceImpl")
	private OrderService orderService;

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("payment", paymentService.find(id));
		return "/admin/payment/view";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		//获取当前账户、商户、订单信息
		List<Filter> filters=orderService.getFilters();
		pageable.setFilters(filters);
		model.addAttribute("page", paymentService.findPage(pageable));
		return "/admin/payment/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				Payment payment = paymentService.find(id);
				if (payment != null && payment.getExpire() != null && !payment.hasExpired()) {
					return Message.error("admin.payment.deleteUnexpiredNotAllowed");
				}
			}
			paymentService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

}