package net.shopxx.dao.impl;

import org.springframework.stereotype.Repository;

import net.shopxx.dao.SensitivityDao;
import net.shopxx.entity.Sensitivity;

/**
 * Dao - 敏感词
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Repository("sensitivityDaoImpl")
public class SensitivityDaoImpl extends BaseDaoImpl<Sensitivity, Long> implements SensitivityDao {

}
