/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao;

import net.shopxx.entity.virtual.VirAccount;

/**
 * Dao - 订单
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface VirAccountDao extends BaseDao<VirAccount, Long> {

	/**
	 * 根据订单编号查找订单
	 * 
	 * @param sn
	 *            订单编号(忽略大小写)
	 * @return 订单，若不存在则返回null
	 */
	VirAccount findBySn(String sn);
}