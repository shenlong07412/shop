/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.OwnerPayMentLogDao;
import net.shopxx.entity.OwnerPaymentLog;
import net.shopxx.service.OwnerPaymentLogService;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

/**
 * Service - 业主缴费日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("ownerPaymentLogServiceImpl")
public class OwnerPaymentLogServiceImpl extends BaseServiceImpl<OwnerPaymentLog, Long> implements OwnerPaymentLogService, DisposableBean {

	@Resource(name = "ownerPaymentLogDaoImpl")
	private OwnerPayMentLogDao ownerPaymentLogDao;
	
	@Resource(name = "ownerPaymentLogDaoImpl")
	public void setBaseDao(OwnerPayMentLogDao ownerPaymentLogDao) {
		super.setBaseDao(ownerPaymentLogDao);
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}


}