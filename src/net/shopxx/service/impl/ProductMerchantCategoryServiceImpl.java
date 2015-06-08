package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.ProductMerchantCategoryDao;
import net.shopxx.entity.ProductMerchantCategory;
import net.shopxx.service.ProductMerchantCategoryService;

import org.springframework.stereotype.Service;

@Service("productMerchantCategoryServiceImpl")
public class ProductMerchantCategoryServiceImpl extends BaseServiceImpl<ProductMerchantCategory, Long> implements ProductMerchantCategoryService {

    @Resource(name = "productMerchantCategoryDaoImpl")
    public void setBaseDao(ProductMerchantCategoryDao productMerchantCategoryDao) {
        super.setBaseDao(productMerchantCategoryDao);
    }
    @Resource(name = "productMerchantCategoryDaoImpl")
    private ProductMerchantCategoryDao productMerchantCategoryDao;

    @Override
    public List<ProductMerchantCategory> findListByMerchantId(Long merchantId) {
        return productMerchantCategoryDao.findListByMerchantId(merchantId);
 
    }

    @Override
    public void deleteByMerchantId(Long merchantId) {
         
        productMerchantCategoryDao.deleteByMerchantId(merchantId);
    }

}
