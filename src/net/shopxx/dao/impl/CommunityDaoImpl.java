package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.shopxx.dao.CommunityDao;
import net.shopxx.entity.Community;
import org.springframework.stereotype.Repository;

/**
 * Dao - 社区
 * 
 * @author lianjsh
 * 
 */
@Repository("communityDaoImpl")
public class CommunityDaoImpl extends BaseDaoImpl<Community, Long> implements CommunityDao {
	public List<Community> findList(Community community) {
		if(community!=null){
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Community> criteriaQuery = criteriaBuilder.createQuery(Community.class);
			Root<Community> root = criteriaQuery.from(Community.class);
			criteriaQuery.select(root);
			if (community.getArea() != null) {
				criteriaQuery.where(criteriaBuilder.equal(root.get("area"), community.getArea()));
			}
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
			return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
		}
		return null;		
	}
}