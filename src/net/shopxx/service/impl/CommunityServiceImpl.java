package net.shopxx.service.impl;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.dao.CommunityDao;
import net.shopxx.entity.Community;
import net.shopxx.service.CommunityService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service -社区
 * 
 * @author chenshw
 * 
 */
@Service("communityServiceImpl")
public class CommunityServiceImpl extends BaseServiceImpl<Community, Long> implements CommunityService {

	@Resource(name = "communityDaoImpl")
	private CommunityDao communityDao;

	@Resource(name = "communityDaoImpl")
	public void setBaseDao(CommunityDao communityDao) {
		super.setBaseDao(communityDao);
	}

	
	//@Transactional(readOnly = true)
	//public Merchant findByUsername(String username) {
	//	return merchantDao.findByUsername(username);
	//}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void save(Community community) {
		super.save(community);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Community update(Community community) {
		return super.update(community);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Community update(Community community, String... ignoreProperties) {
		return super.update(community, ignoreProperties);
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
	public void delete(Community community) {
		super.delete(community);
	}
	
	@Override
	public List<Community> findList(Community community) {		
		return findList(community);
	}
	
	@Transactional(readOnly = true)
	public List<Community> findListForAll(Integer count, List<Filter> filters, List<Order> orders) {
		return super.findList(null, count, filters, orders);
	}

	@Transactional(readOnly = true)
	public List<Community> findListForAll(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
		return super.findList(first, count, filters, orders);
	}
}