package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.PropertyInfoDao;
import net.shopxx.entity.PropertyInfo;
import net.shopxx.service.PropertyInfoService;

import org.springframework.stereotype.Service;

@Service("propertyInfoServiceImpl")
public class PropertyInfoServiceImpl  extends BaseServiceImpl<PropertyInfo, Long> implements PropertyInfoService {
    
  
    @Resource(name = "propertyInfoDaoImpl")
    public void setBaseDao(PropertyInfoDao propertyInfoDao) {
        super.setBaseDao(propertyInfoDao);
    }
    
    

    
    
}
