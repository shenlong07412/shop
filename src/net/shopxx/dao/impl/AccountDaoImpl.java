package net.shopxx.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import net.shopxx.dao.AccountDao;
import net.shopxx.entity.biz.Account;

import org.springframework.stereotype.Repository;

/**
 * Dao - 账号
 * 
 * @author lianjsh
 */
@Repository("accountDaoImpl")
public class AccountDaoImpl extends BaseDaoImpl<Account, Long> implements AccountDao {

    public boolean usernameExists(String username) {
        if (username == null) {
            return false;
        }
        String jqpl = "select count(*) from Account account where lower(account.username) = lower(:username)";
        Long count = entityManager.createQuery(jqpl, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        return count > 0;
    }

    public Account findByUsername(String username) {
        if (username == null) {
            return null;
        }
        try {
            String jpql = "select a from Account a where lower(a.username) = lower(:username)";
            return entityManager.createQuery(jpql, Account.class).setFlushMode(FlushModeType.COMMIT).setParameter("username",
                                                                                                                  username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
