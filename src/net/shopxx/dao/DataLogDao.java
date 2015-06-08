/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.dao;

import net.shopxx.entity.DataLog;


/**
 * Dao - 数据日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface DataLogDao extends BaseDao<DataLog, Long> {
	
	/**
	 * 删除所有日志
	 */
	void removeAll();

}