package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.MemberCertificationDao;
import net.shopxx.dao.MemberDao;
import net.shopxx.entity.MemberCertification;
import net.shopxx.service.MemberCertificationService;

import org.springframework.stereotype.Service;

@Service("memberCertificationServiceImpl")
public class MemberCertificationServiceImpl extends BaseServiceImpl<MemberCertification, Long> implements MemberCertificationService {

    @Resource(name = "memberCertificationDaoImpl")
    private MemberCertificationDao memberCertificationDao;

    @Resource(name = "memberCertificationDaoImpl")
    public void setBaseDao(MemberCertificationDao memberCertificationDao) {
        super.setBaseDao(memberCertificationDao);
    }

    @Override
    public List<MemberCertification> getListByMemberId(Long memberId) {
        List<MemberCertification> result = memberCertificationDao.findListByMemberId(memberId);
        return result;
    }
}
