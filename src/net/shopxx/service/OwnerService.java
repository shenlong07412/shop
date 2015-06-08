package net.shopxx.service;

import net.shopxx.entity.OwnerInfo;

public interface OwnerService extends BaseService<OwnerInfo, Long>{
	
	/**
	 * 判断业主编号是否唯一
	 * 
	 * @param previousNo
	 *            修改前业主编号(忽略大小写)
	 * @param currentNo
	 *            当前业主编号(忽略大小写)
	 * @return 业主编号是否唯一
	 */
	boolean noUnique(String previousNo, String currentNo);

}
