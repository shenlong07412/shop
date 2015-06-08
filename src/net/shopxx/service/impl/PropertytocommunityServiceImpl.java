package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.PropertytocommunityDao;
import net.shopxx.entity.Propertytocommunity;
import net.shopxx.service.PropertytocommunityService;

import org.springframework.stereotype.Service;

@Service("propertytocommunityServiceImpl")
public class PropertytocommunityServiceImpl extends BaseServiceImpl<Propertytocommunity, Long> implements PropertytocommunityService {
 
    @Resource(name = "propertytocommunityDaoImpl")
    private PropertytocommunityDao propertytocommunityDao;

    @Resource(name = "propertytocommunityDaoImpl")
    public void setBaseDao(PropertytocommunityDao propertytocommunityDao) {
        super.setBaseDao(propertytocommunityDao);
    }
    
    @Override
    public void deleteByPropertyId(Long propertyId) {
        propertytocommunityDao.deleteByPropertyId(propertyId);
        
    }

    @Override
    public List<Propertytocommunity> findByPropertyId(Long propertyId) {
 
        return propertytocommunityDao.findByPropertyId(propertyId);
    }

    @Override
    public void deleteByCommunityId(Long communityId) {
        propertytocommunityDao.deleteByCommunityId(communityId);
        
    }

    @Override
    public List<Propertytocommunity> findByCommunityId(Long communityId) {
 
        return propertytocommunityDao.findByCommunityId(communityId);
    }
}
