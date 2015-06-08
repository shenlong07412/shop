package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.MemberOAuthDao;
import net.shopxx.entity.MemberOAuth;
import net.shopxx.service.MemberOAuthService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
@Service("memberOAuthServiceImpl")
public class MemberOAuthServiceImpl extends BaseServiceImpl<MemberOAuth, Long>  implements MemberOAuthService, DisposableBean {
	
	@Resource(name = "memberOAuthDaoImpl")
	private MemberOAuthDao memberOAuthDao;
	
	@Resource(name = "memberOAuthDaoImpl")
	public void setBaseDao( MemberOAuthDao memberOAuthDao) {
		super.setBaseDao(memberOAuthDao);
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberOAuth getInfoByMemberIdToken(Long memberId,String token) {
		if(StringUtils.isNotBlank(token)){
			return memberOAuthDao.getInfoByMemberIdToken(memberId,token);
		}
		return null;
	}

	@Override
	public MemberOAuth getInfoByToken(String token) {
		if(StringUtils.isNotBlank(token)){
			return memberOAuthDao.getInfoByToken(token);
		}
		return null;
	}


}
