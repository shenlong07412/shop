/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.controller.shop.member;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Member;
import net.shopxx.entity.Receiver;
import net.shopxx.entity.biz.Merchant;
import net.shopxx.entity.virtual.VirAccount;
import net.shopxx.entity.virtual.VirTradeLog;
import net.shopxx.service.MemberService;
import net.shopxx.service.MerchantService;
import net.shopxx.service.ReceiverService;
import net.shopxx.service.VirAccountService;
import net.shopxx.service.VirTradeLogService;
import net.shopxx.util.PageableUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;







import com.alibaba.fastjson.JSON;

/**
 * Controller - 会员中心 - 收货地址
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("shopMemberVirtualController")
@RequestMapping("/member/virtual")
public class VirtualController extends BaseController {

	/** 每页记录数 */
	private static final int PAGE_SIZE = 10;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "virAccountServiceImpl")
	private VirAccountService virAccountService;
	@Resource(name = "virTradeLogServiceImpl")
	private VirTradeLogService virTradeLogService;
	
	
	@Resource(name = "receiverServiceImpl")
	private ReceiverService receiverService;
	@Resource(name = "merchantServiceImpl")
	private MerchantService   merchantService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer pageNumber, ModelMap model) {
		Member member = memberService.getCurrent();
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		
		PageableUtils.setFilter(pageable,Filter.Operator.eq, "member", member);
		PageableUtils.setOrders(pageable,"serialNumber", Order.Direction.asc);
		
		model.addAttribute("page", virAccountService.findPage(pageable));
		return "shop/member/virtual/list";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/listLog", method = RequestMethod.GET)
	public String listLog(Integer pageNumber, Long virtualid,ModelMap model) {
		//Member member = memberService.getCurrent();
		Pageable pageable = new Pageable(pageNumber, PAGE_SIZE);
		VirAccount virAccount=new VirAccount();
		virAccount.setId(virtualid);
		PageableUtils.setFilter(pageable,Filter.Operator.eq, "virAccount", virAccount);
		//PageableUtils.setOrders(pageable,"serialNumber", Order.Direction.asc);
		model.addAttribute("virtualid", virtualid);
		model.addAttribute("page", virTradeLogService.findPage(pageable));
		return "shop/member/virtual/loglist";
	}
	
	
	/**
	 * 充值
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public String recharge(ModelMap model,Long id) {
		VirAccount virAccount = virAccountService.find(id);
		//Member member = memberService.getCurrent();
		model.addAttribute("vitual", virAccount);
		return "shop/member/virtual/recharge";
	}
	
	/**
	 * 充值
	 */
	@RequestMapping(value = "/saveRecharge", method = RequestMethod.POST)
	public String saveRecharge(ModelMap model,Long id,String amount,RedirectAttributes redirectAttributes,String content) {
		VirAccount virAccount = virAccountService.find(id);
		Member member = memberService.getCurrent();
		
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			virAccount.setLastRechargeIp(addr.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		virAccountService.recharge(virAccount, new BigDecimal(amount));
		
		VirTradeLog virTradeLog=new VirTradeLog();		
		virTradeLog.setVirAccount(virAccount);
		virTradeLog.setType(VirTradeLog.Type.recharge);
		virTradeLog.setOperator(member.getName());
		if(content==null){
			virTradeLog.setContent(member.getName()+"对虚拟账号 "+virAccount.getSerialNumber()+"充值"+new BigDecimal(amount));
		}else{
			virTradeLog.setContent(content);
		}
		virTradeLog.setRebateAmount(new BigDecimal(0));
		virTradeLog.setRechargeAmount(new BigDecimal(amount));
		virTradeLogService.save(virTradeLog);
		model.addAttribute("vitual", virAccount);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		return "redirect:recharge.jhtml?id="+id;
	}
	
	/**
	 * 添加虚拟用户
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Pageable pageable,RedirectAttributes redirectAttributes,ModelMap model) {
		Member member = memberService.getCurrent();
		
		//筛选只审核的
		PageableUtils.setFilter(pageable,Filter.Operator.eq, "auditSwitch", 1);
		PageableUtils.setOrders(pageable,"name", Order.Direction.asc);
		
        model.addAttribute("mList",merchantService.findList(null, pageable.getFilters(), pageable.getOrders()));
        model.addAttribute("member",member);
        
		return "shop/member/virtual/add";
	}


	/**
	 * 保存虚拟用户
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long merchantId,String nickname,String tradePasswd, RedirectAttributes redirectAttributes) {
		VirAccount virAccount=new VirAccount();
		Member member = memberService.getCurrent();
		Merchant merchant= merchantService.find(merchantId);
		if(merchant!=null){
			virAccount.setMerchant(merchant);
			virAccount.setSerialNumber("Mb"+member.getId()+"Mc"+merchant.getId());
		}		
		virAccount.setTradePasswd(tradePasswd);
		virAccount.setNickname(nickname);
		virAccount.setMember(member);
		virAccount.setRebateAmounts(new BigDecimal(0));
		virAccount.setRechargeAmounts(new BigDecimal(0));
		virAccount.setStatus(0);
		virAccountService.save(virAccount);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
     * 查找虚拟用户是否存在
     */
    @RequestMapping("/fingSameName")
	@ResponseBody
	public Map<String, Object> fingSameName(HttpServletRequest request,String sn) {
    	
    	Map<String, Object> resultMap = new HashMap<String, Object>();

		VirAccount virAccount= virAccountService.findBySn(sn);
		if(virAccount!=null){
		  resultMap.put("contains", "yes");
		  return resultMap;
		}
		resultMap.put("contains", "no");
		return resultMap;
	}
	
	
	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model, RedirectAttributes redirectAttributes) {
		VirAccount virAccount = virAccountService.find(id);
		if (virAccount == null) {
			return ERROR_VIEW;
		}
		Member member = memberService.getCurrent();
		model.addAttribute("member", member);
		model.addAttribute("virAccount", virAccount);
		return "shop/member/virtual/show";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Receiver receiver, Long id,RedirectAttributes redirectAttributes,String nickname) {
		VirAccount virAccount = virAccountService.find(id);
		if (nickname != null) {
			virAccount.setNickname(nickname);
		}
		virAccountService.update(virAccount);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	
	
	/**
     * 列表
     */
    @RequestMapping(value = "/listMerchant", method = RequestMethod.GET)
	public @ResponseBody  String listMerchant(Long auditSwitch, Pageable pageable) {

    	PageableUtils.setFilter(pageable,Filter.Operator.eq, "auditSwitch", 1);
    	PageableUtils.setOrders(pageable,"name", Order.Direction.desc);          
        return JSON.toJSONString(merchantService.findList(null, pageable.getFilters(), pageable.getOrders()));       
    }

	
}