/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.dao;

import net.shopxx.entity.OwnerInfo;

/**
 * Dao - 业主
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface OwnerDao extends BaseDao<OwnerInfo, Long> {
	
	/**
	 * 判断业主编号是否唯一
	 * 
	 * @param currentNo
	 *            当前业主编号(忽略大小写)
	 * @return 业主编号是否唯一
	 */
	boolean noUnique(String currentNo);


}