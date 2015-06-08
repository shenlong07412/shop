package net.shopxx.dao;

import net.shopxx.entity.MemberOAuth;

/**
 * 会员授权
 * @author
 *
 * 2014-7-22
 */
public interface MemberOAuthDao extends BaseDao<MemberOAuth, Long>{
	
	/**
	 * 根据会员id和token获取会员授权信息
	 * @param memberId
	 * @param token
	 * @return
	 */
	MemberOAuth getInfoByMemberIdToken(Long memberId,String token);
	
	/**
	 * 根据token获取会员授权信息
	 * @param token
	 * @return
	 */
	MemberOAuth getInfoByToken(String token);

}
