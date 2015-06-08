/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import net.shopxx.dao.DepositDao;
import net.shopxx.dao.MemberDao;
import net.shopxx.dao.VirAccountDao;
import net.shopxx.dao.VirTradeLogDao;
import net.shopxx.entity.Deposit;
import net.shopxx.entity.Member;
import net.shopxx.entity.virtual.VirAccount;
import net.shopxx.service.VirAccountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 订单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("virAccountServiceImpl")
public class VirAccountServiceImpl extends BaseServiceImpl<VirAccount, Long> implements VirAccountService {

	@Resource(name = "virAccountDaoImpl")
	private VirAccountDao virAccountDao;
	@Resource(name = "memberDaoImpl")
	private MemberDao memberDao;
	@Resource(name = "depositDaoImpl")
	private DepositDao depositDao;
	@Resource(name = "virTradeLogDaoImpl")
	private	VirTradeLogDao virTradeLogDao;
	
	@Resource(name = "virAccountDaoImpl")
	public void setBaseDao(VirAccountDao virAccountDao) {
		super.setBaseDao(virAccountDao);
	}

	@Transactional(readOnly = true)
	public VirAccount findBySn(String sn) {
		return virAccountDao.findBySn(sn);
	}
	
	@Override
	public void recharge(VirAccount virAccount,BigDecimal amount) {
		BigDecimal rechargeAmounts=virAccount.getRechargeAmounts().add(amount);
		virAccount.setRechargeAmounts(rechargeAmounts);
		virAccount.setLastRechargeDate(new Date());
		super.update(virAccount);
		
		Member member = virAccount.getMember();
		memberDao.lock(member, LockModeType.PESSIMISTIC_WRITE);
		member.setBalance(member.getBalance().subtract(amount));
		memberDao.merge(member);
		
		//设置我的 预存款记录
		Deposit deposit = new Deposit();
		deposit.setType(Deposit.Type.memberVirtual);
		deposit.setCredit(new BigDecimal(0));
		deposit.setDebit(amount);
		deposit.setBalance(member.getBalance());
		deposit.setOperator(member.getName());
		deposit.setMember(member);
		//deposit.setOrder(order);
		depositDao.persist(deposit);
	}
}