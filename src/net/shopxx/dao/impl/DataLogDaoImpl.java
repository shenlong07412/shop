package net.shopxx.dao.impl;

import javax.persistence.FlushModeType;

import net.shopxx.dao.DataLogDao;
import net.shopxx.entity.DataLog;

import org.springframework.stereotype.Repository;

@Repository("dataLogDaoImpl")
public class DataLogDaoImpl extends BaseDaoImpl<DataLog, Long> implements DataLogDao {
	
	public void removeAll() {
		String jpql = "delete from DataLog log";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

}
