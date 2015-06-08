package net.shopxx.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.AccountDao;
import net.shopxx.entity.Admin;
import net.shopxx.entity.Role;
import net.shopxx.entity.biz.Account;
import net.shopxx.service.AccountService;
import net.shopxx.shiro.Principal;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service - 账号
 * 
 * @author lianjsh
 */
@Service("accountServiceImpl")
public class AccountServiceImpl extends BaseServiceImpl<Account, Long> implements AccountService {

    @Resource(name = "accountDaoImpl")
    private AccountDao accountDao;
    

    @Resource(name = "accountDaoImpl")
    public void setBaseDao(AccountDao accountDao) {
        super.setBaseDao(accountDao);
    }

    @Transactional(readOnly = true)
    public boolean usernameExists(String username) {
        return accountDao.usernameExists(username);
    }

    @Transactional(readOnly = true)
    public Account findByUsername(String username) {
        return accountDao.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Account getCurrent() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return accountDao.find(principal.getId());
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public String getCurrentUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal.getUsername();
            }
        }
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void save(Account account) {
        super.save(account);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Account update(Account account) {
        return super.update(account);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Account update(Account account, String... ignoreProperties) {
        return super.update(account, ignoreProperties);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Long... ids) {
        super.delete(ids);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void delete(Account account) {
        super.delete(account);
    }

    @Override
    public boolean AccountIsLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return subject.isAuthenticated();
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.AccountService#findAuthorities(java.lang.Long)
     */
    @Transactional(readOnly = true)
    public List<String> findAuthorities(Long id) {
        List<String> authorities = new ArrayList<String>();
        Account admin = accountDao.find(id);
        if (admin != null) {
            for (Role role : admin.getRoles()) {
                authorities.addAll(role.getAuthorities());
            }
        }
        return authorities;
    }

}
