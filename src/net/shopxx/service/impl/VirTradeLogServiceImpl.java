/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.VirTradeLogDao;
import net.shopxx.entity.virtual.VirTradeLog;
import net.shopxx.service.VirTradeLogService;

import org.springframework.stereotype.Service;

/**
 * Service - 订单日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("virTradeLogServiceImpl")
public class VirTradeLogServiceImpl extends BaseServiceImpl<VirTradeLog, Long> implements VirTradeLogService {

	@Resource(name = "virTradeLogDaoImpl")
	public void setBaseDao(VirTradeLogDao virTradeLogDao) {
		super.setBaseDao(virTradeLogDao);
	}

}