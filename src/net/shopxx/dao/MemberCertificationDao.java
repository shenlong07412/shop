package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.MemberCertification;

public interface MemberCertificationDao extends BaseDao<MemberCertification, Long> {

    /**
     * 根据memberId查找会员实名认证信息
     * 
     */
    List<MemberCertification> findListByMemberId(Long memberId);

}
