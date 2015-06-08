package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.MemberCertification;

public interface MemberCertificationService extends BaseService<MemberCertification, Long> {

    List<MemberCertification> getListByMemberId(Long memberId);
}
