package net.shopxx.dao.impl;

import net.shopxx.dao.OwnerPayMentLogDao;
import net.shopxx.entity.OwnerPaymentLog;

import org.springframework.stereotype.Repository;

/**
 * Dao - 业主缴费日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("ownerPaymentLogDaoImpl")
public class OwnerPaymentLogDaoImpl extends BaseDaoImpl<OwnerPaymentLog, Long> implements OwnerPayMentLogDao {

}
