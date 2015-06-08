package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.AccountDao;
import net.shopxx.dao.MerchantDao;
import net.shopxx.entity.biz.Account;
import net.shopxx.entity.biz.Merchant;
import net.shopxx.service.MerchantService;
import net.shopxx.shiro.Principal;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Service - 商户
 * 
 * @author lianjsh
 */
@Service("merchantServiceImpl")
public class MerchantServiceImpl extends BaseServiceImpl<Merchant, Long> implements MerchantService {

    @Resource(name = "merchantDaoImpl")
    private MerchantDao merchantDao;

    @Resource(name = "accountDaoImpl")
    private AccountDao  accountDao;

    @Resource(name = "merchantDaoImpl")
    public void setBaseDao(MerchantDao merchantDao) {
        super.setBaseDao(merchantDao);
    }

    @Transactional(readOnly = true)
    public boolean usernameExists(String username) {
        return merchantDao.usernameExists(username);
    }

    @Transactional(readOnly = true)
    public Merchant findByUsername(String username) {
        return merchantDao.findByUsername(username);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public void save(Merchant merchant) {
        super.save(merchant);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Merchant update(Merchant merchant) {
        return super.update(merchant);
    }

    @Override
    @Transactional
    @CacheEvict(value = "authorization", allEntries = true)
    public Merchant update(Merchant merchant, String... ignoreProperties) {
        return super.update(merchant, ignoreProperties);
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
    public void delete(Merchant merchant) {
        super.delete(merchant);
    }

    @Override
    public List<Merchant> findList(Merchant merchant, Integer count, List<Filter> filters, List<Order> orders) {
        // TODO Auto-generated method stub
        return merchantDao.findList(merchant, count, filters, orders);
    }

    @Override
    public Page<Merchant> findPage(Merchant merchant, Pageable pageable) {
        // TODO Auto-generated method stub
        return merchantDao.findPage(merchant, pageable);
    }

    @Override
    public Page<Merchant> findPage(Integer checkStus, Integer recommendStatus, Pageable pageable) {
        // TODO Auto-generated method stub
        return merchantDao.findPage(checkStus, recommendStatus, pageable);
    }

    @Override
    public Merchant getCurrent() {
        Subject subject = SecurityUtils.getSubject();
        Object userType = SecurityUtils.getSubject().getSession().getAttribute("userType");
        if (subject != null && StringUtils.isEmpty(userType) && "biz".equals(userType)) {
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                Account account = accountDao.find(principal.getId());
                if (account != null) {
                    Merchant merchant = account.getMerchant();
                    return merchant;
                }
            }
        }
        return null;
    }
}
