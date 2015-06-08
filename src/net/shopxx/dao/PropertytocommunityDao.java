package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.Propertytocommunity;


public interface PropertytocommunityDao extends BaseDao<Propertytocommunity, Long>{

    public void  deleteByPropertyId(Long propertyId);
    
    public List<Propertytocommunity>  findByPropertyId(Long propertyId);
    
    public void  deleteByCommunityId(Long communityId);
    
    public List<Propertytocommunity>  findByCommunityId(Long communityId);
    
    
}
