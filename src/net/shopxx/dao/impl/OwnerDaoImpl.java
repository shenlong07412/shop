package net.shopxx.dao.impl;

import javax.persistence.FlushModeType;

import net.shopxx.dao.OwnerDao;
import net.shopxx.entity.OwnerInfo;

import org.springframework.stereotype.Repository;

/**
 * Dao - 业主
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("ownerDaoImpl")
public class OwnerDaoImpl extends BaseDaoImpl<OwnerInfo, Long> implements OwnerDao {

	@Override
	public boolean noUnique(String currentNo) {
		if (currentNo == null) {
			return false;
		}
		String jpql = "select count(*) from OwnerInfo owner where lower(owner.no) = lower(:no)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("no", currentNo).getSingleResult();
		return count > 0;
	}

}
