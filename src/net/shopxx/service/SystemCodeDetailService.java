package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.SystemCodeDetail;
/**
 * Service - 字典
 * 
 * @author chenshw
 * 
 */
public interface SystemCodeDetailService extends BaseService<SystemCodeDetail, Long> {
	List<SystemCodeDetail> getListBySystemCode(String systemCode);
	SystemCodeDetail getByCodeDetail(String codeDetail);


}