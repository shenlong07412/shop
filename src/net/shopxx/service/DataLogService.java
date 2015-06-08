/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service;

import net.shopxx.entity.DataLog;

/**
 * Service - 数据日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface DataLogService extends BaseService<DataLog, Long>{

	/**
	 * 清空日志
	 */
	void clear();

}