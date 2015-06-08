/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.LogConfig;

/**
 * Service - 日志配置
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface LogConfigService extends BaseService<LogConfig, Long>{

	/**
	 * 获取所有日志配置
	 * 
	 * @return 所有日志配置
	 */
	List<LogConfig> getAll();

}