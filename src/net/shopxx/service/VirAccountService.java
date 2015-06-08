/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service;

import java.math.BigDecimal;

import net.shopxx.entity.virtual.VirAccount;

/**
 * Service - 虚拟账号
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface VirAccountService extends BaseService<VirAccount, Long> {

	/**
	 * 根据编号查找虚拟账号
	 * 
	 * @param sn
	 *            订单编号(忽略大小写)
	 * @return 若不存在则返回null
	 */
	VirAccount findBySn(String sn);

	/**
	 * 虚拟账号充值
	 * @param virAccount	虚拟账号
	 * @param amount	充值金额
	 */
	public void recharge(VirAccount virAccount,BigDecimal amount);
}