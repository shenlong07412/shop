package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.Propertytocommunity;


public interface PropertytocommunityService extends BaseService<Propertytocommunity, Long> {
    public void  deleteByPropertyId(Long propertyId);
    
    public List<Propertytocommunity>  findByPropertyId(Long propertyId);
    
    public void  deleteByCommunityId(Long communityId);
    
    public List<Propertytocommunity>  findByCommunityId(Long communityId);
    
}
