package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.biz.Merchant;

/**
 * Entity - 授权商品分类
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_product_merchant_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_product_merchant_category")
public class ProductMerchantCategory extends OrderEntity {

    private static final long serialVersionUID = 538109460087172340L;

    private Merchant merchant;
    
    private ProductCategory productCategory;
    
    private Long status;

    @ManyToOne(fetch = FetchType.LAZY)
    public Merchant getMerchant() {
        return merchant;
    }

    
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    
    public Long getStatus() {
        return status;
    }

    
    public void setStatus(Long status) {
        this.status = status;
    }
    
    
    
}
