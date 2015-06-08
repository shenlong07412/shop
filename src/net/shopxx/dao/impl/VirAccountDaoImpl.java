/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.dao.impl;


import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import net.shopxx.dao.VirAccountDao;
import net.shopxx.entity.virtual.VirAccount;
import org.springframework.stereotype.Repository;

/**
 * Dao - 虚拟账号
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("virAccountDaoImpl")
public class VirAccountDaoImpl extends BaseDaoImpl<VirAccount, Long> implements VirAccountDao {

	public VirAccount findBySn(String sn) {
		if (sn == null) {
			return null;
		}
		String jpql = "select virAccount from VirAccount virAccount where lower(virAccount.serialNumber) = lower(:sn)";
		try {
			return entityManager.createQuery(jpql, VirAccount.class).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}