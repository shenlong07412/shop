package net.shopxx.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import net.shopxx.dao.MemberCertificationDao;
import net.shopxx.entity.Member;
import net.shopxx.entity.MemberCertification;

@Repository("memberCertificationDaoImpl")
public class MemberCertificationDaoImpl extends BaseDaoImpl<MemberCertification, Long> implements MemberCertificationDao {

    @Override
    public List<MemberCertification> findListByMemberId(Long memberId) {
        if (memberId == null) {
            return Collections.<MemberCertification> emptyList();
        }
        String jpql = "select mf from MemberCertification mf where mf.memberId = :memberId order by mf.id desc";
        return entityManager.createQuery(jpql, MemberCertification.class).setFlushMode(FlushModeType.COMMIT).setParameter("memberId",
                                                                                                                          memberId).getResultList();
    }

}
