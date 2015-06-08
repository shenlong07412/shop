package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;

import net.shopxx.dao.ProductMerchantCategoryDao;
import net.shopxx.entity.ProductMerchantCategory;

import org.springframework.stereotype.Repository;


/**
 * Dao - 商品类别授权关系
 * 
 * @author fangym
 * @version 1.0
 */
@Repository("productMerchantCategoryDaoImpl")
public class ProductMerchantCategoryDaoImpl extends BaseDaoImpl<ProductMerchantCategory, Long> implements ProductMerchantCategoryDao {

    @Override
    public List<ProductMerchantCategory> findListByMerchantId(Long merchantId) {
        if(merchantId==null){
            return null;
        }
        String jpql = "select productMerchantCategory from ProductMerchantCategory productMerchantCategory where productMerchantCategory.merchant.id =:merchantId";
        return  entityManager.createQuery(jpql, ProductMerchantCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("merchantId", merchantId).getResultList();
 
    }

    @Override
    public void deleteByMerchantId(Long merchantId) {
        if(merchantId==null){
            return ;
        }
        String jpql = "delete ProductMerchantCategory productMerchantCategory where productMerchantCategory.merchant.id =:merchantId";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("merchantId", merchantId).executeUpdate();
    }

    

}
