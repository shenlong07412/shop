/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao.impl;

import net.shopxx.dao.VirTradeLogDao;
import net.shopxx.entity.virtual.VirTradeLog;

import org.springframework.stereotype.Repository;

/**
 * Dao - 虚拟交易记录
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("virTradeLogDaoImpl")
public class VirTradeLogDaoImpl extends BaseDaoImpl<VirTradeLog, Long> implements VirTradeLogDao {

}