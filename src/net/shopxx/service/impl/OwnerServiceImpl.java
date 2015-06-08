/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.OwnerDao;
import net.shopxx.entity.OwnerInfo;
import net.shopxx.service.OwnerService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

/**
 * Service - 业主
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("ownerServiceImpl")
public class OwnerServiceImpl extends BaseServiceImpl<OwnerInfo, Long> implements OwnerService, DisposableBean {

	@Resource(name = "ownerDaoImpl")
	private OwnerDao ownerDao;
	
	@Resource(name = "ownerDaoImpl")
	public void setBaseDao(OwnerDao ownerDao) {
		super.setBaseDao(ownerDao);
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean noUnique(String previousNo, String currentNo) {
		if (StringUtils.equalsIgnoreCase(previousNo, currentNo)) {
			return true;
		} else {
			if (ownerDao.noUnique(currentNo)) {
				return false;
			} else {
				return true;
			}
		}
	}


}