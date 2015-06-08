/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Pageable;
import net.shopxx.entity.virtual.VirAccount;
import net.shopxx.entity.virtual.VirTradeLog;
import net.shopxx.entity.Admin;
import net.shopxx.entity.Member;
import net.shopxx.service.AdminService;
import net.shopxx.service.VirAccountService;
import net.shopxx.service.VirTradeLogService;
import net.shopxx.util.PageableUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 虚拟账号
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminVirAccountController")
@RequestMapping("/admin/virAccount")
public class VirAccountController extends BaseController {


	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "virAccountServiceImpl")
	private VirAccountService virAccountService;
	@Resource(name = "virTradeLogServiceImpl")
	private VirTradeLogService virTradeLogService;
	
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		VirAccount virAccount = virAccountService.find(id);
		//Member member = memberService.getCurrent();
		model.addAttribute("vitual", virAccount);
		return "/admin/virtual/edit";
	}

	
	
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Long id,String amount,String content,ModelMap model,RedirectAttributes redirectAttributes,String recruitMap) {
		
		VirAccount virAccount = virAccountService.find(id);
		Admin admin = adminService.getCurrent();
		
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			virAccount.setLastRechargeIp(addr.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal rebateAmounts=virAccount.getRebateAmounts().add(new BigDecimal(amount));
		virAccount.setRebateAmounts(rebateAmounts);
		virAccount.setLastRechargeDate(new Date());
		virAccountService.update(virAccount);
		
		VirTradeLog virTradeLog=new VirTradeLog();		
		virTradeLog.setVirAccount(virAccount);
		virTradeLog.setType(VirTradeLog.Type.rebate);
		virTradeLog.setOperator(admin.getName());
		if(content==null){
			virTradeLog.setContent(admin.getName()+"对虚拟账号 "+virAccount.getSerialNumber()+"充值"+new BigDecimal(amount));
		}else{
			virTradeLog.setContent(content);
		}
		virTradeLog.setRebateAmount(new BigDecimal(amount));
		virTradeLog.setRechargeAmount(new BigDecimal(0));
		virTradeLogService.save(virTradeLog);
		model.addAttribute("vitual", virAccount);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		return "redirect:list.jhtml";
	}

	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model,Long merchantId,String memberName) {
		//还要根据当前用户获取商家Id并筛选
		//PageableUtils.setFilter(pageable, Filter.Operator.eq, "merchant", merchant);
		if(!StringUtils.isEmpty(memberName)){
			Member member =new Member();
			member.setName(memberName);
			PageableUtils.setFilter(pageable, Filter.Operator.eq, "member", member);
		}
		model.addAttribute("page",virAccountService.findPage(pageable));
		return "/admin/virtual/list";
	}
		
	/**
	 * 交易记录列表
	 */
	@RequestMapping(value = "/listLog", method = RequestMethod.GET)
	public String listLog(Pageable pageable,Integer pageNumber, Long virtualid,ModelMap model) {

		VirAccount virAccount=new VirAccount();
		virAccount.setId(virtualid);
		PageableUtils.setFilter(pageable,Filter.Operator.eq, "virAccount", virAccount);

		model.addAttribute("virtualid", virtualid);
		model.addAttribute("page", virTradeLogService.findPage(pageable));
		return "/admin/virtual/loglist";
	}
}