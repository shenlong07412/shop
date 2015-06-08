package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.DataLogDao;
import net.shopxx.entity.DataLog;
import net.shopxx.service.DataLogService;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;


/**
 * Service - 数据日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("dataLogServiceImpl")
public class DataLogServiceImpl extends BaseServiceImpl<DataLog, Long> implements DataLogService , DisposableBean{
	
	
	@Resource(name = "dataLogDaoImpl")
	private DataLogDao dataLogDao;
	
	@Resource(name = "dataLogDaoImpl")
	public void setBaseDao(DataLogDao dataLogDao) {
		super.setBaseDao(dataLogDao);
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		dataLogDao.removeAll();
	}


}
