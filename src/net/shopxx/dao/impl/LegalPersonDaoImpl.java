package net.shopxx.dao.impl;
import net.shopxx.dao.LegalPersonDao;
import net.shopxx.entity.biz.LegalPerson;

import org.springframework.stereotype.Repository;

/**
 * Dao - 商户的企业法人信息
 * 
 * @author lianjsh
 * 
 */
@Repository("legalPersonDaoImpl")
public class LegalPersonDaoImpl extends BaseDaoImpl<LegalPerson, Long> implements LegalPersonDao {

}