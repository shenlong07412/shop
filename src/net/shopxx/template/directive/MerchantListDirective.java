/*
 * Copyright 2014 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Created on 2014年7月16日
// $Id$

package net.shopxx.template.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.entity.Article;
import net.shopxx.entity.Attribute;
import net.shopxx.entity.Brand;
import net.shopxx.entity.Product;
import net.shopxx.entity.ProductCategory;
import net.shopxx.entity.Promotion;
import net.shopxx.entity.Tag;
import net.shopxx.entity.Product.OrderType;
import net.shopxx.entity.biz.Merchant;
import net.shopxx.service.AttributeService;
import net.shopxx.service.BrandService;
import net.shopxx.service.MerchantService;
import net.shopxx.service.ProductCategoryService;
import net.shopxx.service.ProductService;
import net.shopxx.service.PromotionService;
import net.shopxx.service.TagService;
import net.shopxx.util.FreemarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 商家标签
 * 
 * @author czllfy
 */

@Component("merchantListDirective")
public class MerchantListDirective extends BaseDirective {

    /** "商品分类ID"参数名称 */
    private static final String    PRODUCT_CATEGORY_ID_PARAMETER_NAME = "productCategoryId";

    /** "属性值"参数名称 */
    private static final String    ATTRIBUTE_VALUE_PARAMETER_NAME     = "attributeValue";

    /** "排序类型"参数名称 */
    private static final String    ORDER_TYPE_PARAMETER_NAME          = "orderType";

    /** 变量名称 */
    private static final String    VARIABLE_NAME                      = "merchants";

    @Resource(name = "merchantServiceImpl")
    private MerchantService        merchantService;

    @Resource(name = "productServiceImpl")
    private ProductService         productService;

    @Resource(name = "productCategoryServiceImpl")
    private ProductCategoryService productCategoryService;
    @Resource(name = "brandServiceImpl")
    private BrandService           brandService;
    @Resource(name = "promotionServiceImpl")
    private PromotionService       promotionService;
    @Resource(name = "attributeServiceImpl")
    private AttributeService       attributeService;
    @Resource(name = "tagServiceImpl")
    private TagService             tagService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
                                                                                                          throws TemplateException,
                                                                                                          IOException {
        Long productCategoryId = FreemarkerUtils.getParameter(PRODUCT_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
        Map<Long, String> attributeValue = FreemarkerUtils.getParameter(ATTRIBUTE_VALUE_PARAMETER_NAME, Map.class,
                                                                        params);
        OrderType orderType = FreemarkerUtils.getParameter(ORDER_TYPE_PARAMETER_NAME, OrderType.class, params);

        ProductCategory productCategory = productCategoryService.find(productCategoryId);
        Map<Attribute, String> attributeValueMap = new HashMap<Attribute, String>();
        if (attributeValue != null) {
            for (Entry<Long, String> entry : attributeValue.entrySet()) {
                Attribute attribute = attributeService.find(entry.getKey());
                if (attribute != null) {
                    attributeValueMap.put(attribute, entry.getValue());
                }
            }
        }

        List<Merchant> merchants;
        if ((productCategoryId != null && productCategory == null)) {
            merchants = new ArrayList<Merchant>();
        } else {
            boolean useCache = useCache(env, params);
            String cacheRegion = getCacheRegion(env, params);
            Integer count = getCount(params);
            List<Filter> filters = getFilters(params, Article.class);
            List<Order> orders = getOrders(params);
            if (useCache) {
                merchants = merchantService.findList(0, count, filters, orders);
            } else {
                merchants = merchantService.findList(0, count, filters, orders);
            }
        }
        setLocalVariable(VARIABLE_NAME, merchants, env, body);
    }

}
