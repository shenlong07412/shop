package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.ProductMerchantCategory;

/**
 * Entity - 授权商品分类
 * 
 * @author fangym
 * @version 1.0
 */
public interface ProductMerchantCategoryDao extends BaseDao<ProductMerchantCategory, Long> {

    public List<ProductMerchantCategory> findListByMerchantId(Long merchantId);
    
    public void  deleteByMerchantId(Long merchantId);
}
