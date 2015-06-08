package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.ProductMerchantCategory;


public interface ProductMerchantCategoryService   extends BaseService<ProductMerchantCategory, Long>{
    
    public List<ProductMerchantCategory> findListByMerchantId(Long merchantId);
    
    public void  deleteByMerchantId(Long merchantId);
}
