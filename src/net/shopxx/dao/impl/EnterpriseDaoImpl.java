package net.shopxx.dao.impl;
import net.shopxx.dao.EnterpriseDao;
import net.shopxx.entity.biz.Enterprise;

import org.springframework.stereotype.Repository;

/**
 * Dao - 商户的企业基本信息
 * 
 * @author lianjsh
 * 
 */
@Repository("enterpriseImpl")
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise, Long> implements EnterpriseDao {

}