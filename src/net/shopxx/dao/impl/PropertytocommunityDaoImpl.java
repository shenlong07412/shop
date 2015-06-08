package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import net.shopxx.dao.PropertytocommunityDao;
import net.shopxx.entity.Propertytocommunity;

import org.springframework.stereotype.Repository;

/**
 * Dao - 物业公司所管理的小区
 * 
 * @author fangym
 * @version 1.0
 */
@Repository("propertytocommunityDaoImpl")
public class PropertytocommunityDaoImpl extends BaseDaoImpl<Propertytocommunity, Long> implements PropertytocommunityDao {

    @Override
    public void deleteByPropertyId(Long propertyId) {
        if (propertyId == null) {
            return  ;
        }
        String jpql = "delete   Propertytocommunity propertytocommunity where propertytocommunity.propertyInfo.id     =  :propertyId )";
        try {
              entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("propertyId", propertyId).executeUpdate();
        } catch (NoResultException e) {
            return  ;
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    @Override
    public List<Propertytocommunity> findByPropertyId(Long propertyId) {
        if (propertyId == null) {
            return  null;
        }
        String jpql = "select propertytocommunity from Propertytocommunity propertytocommunity where propertytocommunity.propertyInfo.id     =  :propertyId )";
        try {
            return  entityManager.createQuery(jpql, Propertytocommunity.class).setFlushMode(FlushModeType.COMMIT).setParameter("propertyId", propertyId).getResultList() ;
        } catch (NoResultException e) {
            return  null;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
 
    }

    @Override
    public void deleteByCommunityId(Long communityId) {
        if (communityId == null) {
            return  ;
        }
        String jpql = "delete   Propertytocommunity propertytocommunity where propertytocommunity.community.id     =  :communityId )";
        try {
            entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("communityId", communityId).executeUpdate() ;
        } catch (NoResultException e) {
            return  ;
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    @Override
    public List<Propertytocommunity> findByCommunityId(Long communityId) {
        if (communityId == null) {
            return  null;
        }
        String jpql = "select propertytocommunity from Propertytocommunity propertytocommunity where propertytocommunity.community.id     =  :communityId )";
        try {
            return  entityManager.createQuery(jpql, Propertytocommunity.class).setFlushMode(FlushModeType.COMMIT).setParameter("communityId", communityId).getResultList() ;
        } catch (NoResultException e) {
            return  null;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
 
    }

 


}
