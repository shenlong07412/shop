package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Entity - OAuth认证
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_member_oauth")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_member_oauth_sequence")
public class MemberOAuth extends BaseEntity{

	private static final long serialVersionUID = 1938520420372520811L;
	
	/***认证token**/
	private String token;
	
	/***认证名称**/
	private String oauthName;
	
	/***会员信息**/
	private Member member;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOauthName() {
		return oauthName;
	}

	public void setOauthName(String oauthName) {
		this.oauthName = oauthName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = false, updatable = false)
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	

}
