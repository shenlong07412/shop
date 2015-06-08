/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.SensitivityDao;
import net.shopxx.entity.Sensitivity;
import net.shopxx.service.SensitivityService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import cn.ffcs.util.KeywordFilter;

/**
 * Service - 敏感词
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("sensitivityServiceImpl")
public class SensitivityServiceImpl extends BaseServiceImpl<Sensitivity, Long> implements SensitivityService, DisposableBean {

	@Resource(name = "sensitivityDaoImpl")
	private SensitivityDao sensitivityDao;
	
	@Resource(name = "sensitivityDaoImpl")
	public void setBaseDao(SensitivityDao sensitivityDao) {
		super.setBaseDao(sensitivityDao);
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
	@Override
	public String replaceSensitivity(String source, String to) {
		if(StringUtils.isNotBlank(source)){
			List<Sensitivity> list = sensitivityDao.findList(null, null, null, null);
			List<String> strList = new ArrayList<String>();
			if(list!=null && list.size()>0){
				for (Sensitivity si : list) {
					strList.add(si.getSearch());
				}
				KeywordFilter filter= new KeywordFilter();
				filter.addKeywords(strList);
				List<String> set = filter.getTxtKeyWords(source);
				if(set.size()>0){
					source = filter.str_replace1(set.toString(), to, source);
				}
			}
		}
		return source;
	}


}