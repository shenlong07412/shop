package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.SystemCodeDetail;



/**
 * 字典表
 * 
 * @author chenshw
 * 
 */
public interface SystemCodeDetailDao extends BaseDao<SystemCodeDetail, Long> {
 
	
	public List<SystemCodeDetail> findListBySystemCode(String systemCode);
	
	public  SystemCodeDetail  findByCodeDetail(String codeDetail);
}