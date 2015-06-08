package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.Community;



/**
 * Dao - 社区
 * 
 * @author chenshw
 * 
 */
public interface CommunityDao extends BaseDao<Community, Long> {
	
	public List<Community> findList(Community community);
}