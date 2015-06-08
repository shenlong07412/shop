package net.shopxx.dao.impl;
import net.shopxx.dao.ChannelDao;
import net.shopxx.entity.Channel;

import org.springframework.stereotype.Repository;

/**
 * Dao - 栏目配置(对商户的分类
 * 
 * @author lianjsh
 * 
 */
@Repository("channelDaoImpl")
public class ChannelDaoImpl extends BaseDaoImpl<Channel, Long> implements ChannelDao {

}