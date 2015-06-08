package net.shopxx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import net.shopxx.dao.MemberOAuthDao;
import net.shopxx.entity.MemberOAuth;

import org.springframework.stereotype.Repository;

@Repository("memberOAuthDaoImpl")
public class MemberOauthDaoImpl extends BaseDaoImpl<MemberOAuth, Long> implements MemberOAuthDao {

	@Override
	public MemberOAuth getInfoByMemberIdToken(Long memberId,String token) {
		if (token == null) {
			return null;
		}
		try {
			String jpql = "select t from MemberOAuth t where lower(t.token) = lower(:token) and lower(t.member) = lower(:memberId)";
			Query query=entityManager.createQuery(jpql, MemberOAuth.class);
			query.setParameter("token", token);
			query.setParameter("memberId", memberId);
			return (MemberOAuth) query.getSingleResult();
			//return entityManager.createQuery(jpql, MemberOAuth.class).setFlushMode(FlushModeType.COMMIT).setParameter("memberId", memberId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}		
	}

	@Override
	public MemberOAuth getInfoByToken(String token) {
		if (token == null) {
			return null;
		}
		try {
			String jpql = "select t from MemberOAuth t where lower(t.token) = lower(:token)";
			return entityManager.createQuery(jpql, MemberOAuth.class).setFlushMode(FlushModeType.COMMIT).setParameter("token", token).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}	
	}

}
