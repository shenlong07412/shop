package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import net.shopxx.dao.SystemCodeDetailDao;
import net.shopxx.entity.SystemCodeDetail;

import org.springframework.stereotype.Repository;

/**
 * 字典表
 * 
 * @author chenshw
 * 
 */
@Repository("systemCodeDetailDaoImpl")
public class SystemCodeDetailDaoImpl extends BaseDaoImpl<SystemCodeDetail, Long> implements SystemCodeDetailDao {

 

    @Override
    public List<SystemCodeDetail> findListBySystemCode(String systemCode) {
        if (systemCode == null) {
            return null;
        }
        String jpql = "select systemCodeDetail from SystemCodeDetail systemCodeDetail where systemCodeDetail.systemCode.id in (select systemCode.id from SystemCode systemCode where lower(systemCode.code) = lower(:code))";
        try {
            return entityManager.createQuery(jpql, SystemCodeDetail.class).setFlushMode(FlushModeType.COMMIT).setParameter("code", systemCode).getResultList();
        } catch (NoResultException e) {
            return null;
        }
 
    }

    @Override
    public SystemCodeDetail findByCodeDetail(String codeDetail) {
        if (codeDetail == null) {
            return null;
        }
        String jpql = "select systemCodeDetail from SystemCodeDetail systemCodeDetail where systemCodeDetail.codeDetail =  lower(:code))";
        try {
            return entityManager.createQuery(jpql, SystemCodeDetail.class).setFlushMode(FlushModeType.COMMIT).setParameter("code", codeDetail).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}