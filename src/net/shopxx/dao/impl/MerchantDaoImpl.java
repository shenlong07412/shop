package net.shopxx.dao.impl;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.shopxx.Filter;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.MerchantDao;
import net.shopxx.entity.biz.Merchant;

import org.springframework.stereotype.Repository;

/**
 * Dao - 商户
 * 
 * @author lianjsh
 * 
 */
@Repository("merchantDaoImpl")
public class MerchantDaoImpl extends BaseDaoImpl<Merchant, Long> implements MerchantDao {

	@Override
	public boolean usernameExists(String name) {
		if (name == null) {
			return false;
		}
		String jpql = "select count(*) from Merchant mer where lower(mer.name) = lower(:name)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("name", name).getSingleResult();
		return count > 0;
	}

	@Override
	public Merchant findByUsername(String name) {
		if (name == null) {
			return null;
		}
		try {
			String jpql = "select merchant from Merchant merchant where lower(merchant.name) = lower(:name)";
			return entityManager.createQuery(jpql, Merchant.class).setFlushMode(FlushModeType.COMMIT).setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Merchant> findList(Merchant merchant, Integer count, List<Filter> filters, List<net.shopxx.Order> orders) {
		if (merchant == null) {
			return Collections.<Merchant> emptyList();
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> criteriaQuery = criteriaBuilder.createQuery(Merchant.class);
		Root<Merchant> root = criteriaQuery.from(Merchant.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("merchant"), merchant));
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

	public Page<Merchant> findPage(Merchant merchant, Pageable pageable) {
		if (merchant == null) {
			return new Page<Merchant>(Collections.<Merchant> emptyList(), 0, pageable);
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> criteriaQuery = criteriaBuilder.createQuery(Merchant.class);
		Root<Merchant> root = criteriaQuery.from(Merchant.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("merchant"), merchant));
		return super.findPage(criteriaQuery, pageable);
	}

	public Page<Merchant> findPage(Integer checkStatus, Integer recommendStatus,Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Merchant> criteriaQuery = criteriaBuilder.createQuery(Merchant.class);
		Root<Merchant> root = criteriaQuery.from(Merchant.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (checkStatus != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("checkStatus"), checkStatus));
		}
		if (recommendStatus != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("recommendStatus"), recommendStatus));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}