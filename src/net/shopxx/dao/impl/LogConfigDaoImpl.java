package net.shopxx.dao.impl;

import net.shopxx.LogConfig;
import net.shopxx.dao.LogConfigDao;

import org.springframework.stereotype.Repository;

@Repository("logConfigDaoImpl")
public class LogConfigDaoImpl extends BaseDaoImpl<LogConfig, Long> implements LogConfigDao {

}
