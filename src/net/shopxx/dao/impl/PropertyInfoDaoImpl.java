package net.shopxx.dao.impl;

import org.springframework.stereotype.Repository;

import net.shopxx.dao.PropertyInfoDao;
import net.shopxx.entity.PropertyInfo;

/**
 * Dao - 物业公司
 * 
 * @author fangym
 * @version 1.0
 */

@Repository("propertyInfoDaoImpl")
public class PropertyInfoDaoImpl extends BaseDaoImpl<PropertyInfo, Long> implements PropertyInfoDao{

}
