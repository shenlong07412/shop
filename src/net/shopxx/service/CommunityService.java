package net.shopxx.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.entity.Community;
/**
 * Service - 社区
 * 
 * @author chenshw
 * 
 */
public interface CommunityService extends BaseService<Community, Long> {
	public List<Community> findList(Community community);
	
	@Transactional(readOnly = true)
	public List<Community> findListForAll(Integer count, List<Filter> filters, List<Order> orders);

	@Transactional(readOnly = true)
	public List<Community> findListForAll(Integer first, Integer count, List<Filter> filters, List<Order> orders);
	
	
}