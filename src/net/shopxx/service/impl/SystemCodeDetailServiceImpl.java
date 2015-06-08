package net.shopxx.service.impl;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.SystemCodeDetailDao;
import net.shopxx.entity.SystemCodeDetail;
import net.shopxx.service.SystemCodeDetailService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service -字典
 * 
 * @author chenshw
 * 
 */
@Service("systemCodeDetailServiceImpl")
public class SystemCodeDetailServiceImpl extends BaseServiceImpl<SystemCodeDetail, Long> implements SystemCodeDetailService {

	@Resource(name = "systemCodeDetailDaoImpl")
	private SystemCodeDetailDao systemCodeDetailDao;

	@Resource(name = "systemCodeDetailDaoImpl")
	public void setBaseDao(SystemCodeDetailDao systemCodeDetailDao) {
		super.setBaseDao(systemCodeDetailDao);
	}

	@Override
	public List<SystemCodeDetail> getListBySystemCode(String systemCode) {
		return systemCodeDetailDao.findListBySystemCode(systemCode);
	}
	
	//@Transactional(readOnly = true)
	//public Merchant findByUsername(String username) {
	//	return merchantDao.findByUsername(username);
	//}

	@Override
	@Transactional
	@CacheEvict(value = "systemCodeDetail", allEntries = true)
	public void save(SystemCodeDetail systemCodeDetail) {
		super.save(systemCodeDetail);
	}

	@Override
	@Transactional
	@CacheEvict(value = "systemCodeDetail", allEntries = true)
	public SystemCodeDetail update(SystemCodeDetail systemCodeDetail) {
		return super.update(systemCodeDetail);
	}

	@Override
	@Transactional
	@CacheEvict(value = "systemCodeDetail", allEntries = true)
	public SystemCodeDetail update(SystemCodeDetail systemCodeDetail, String... ignoreProperties) {
		return super.update(systemCodeDetail, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "systemCodeDetail", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "systemCodeDetail", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "systemCodeDetail", allEntries = true)
	public void delete(SystemCodeDetail systemCodeDetail) {
		super.delete(systemCodeDetail);
	}

    @Override
    public SystemCodeDetail getByCodeDetail(String codeDetail) {
 
        return systemCodeDetailDao.findByCodeDetail(codeDetail);
    }
	
	
}