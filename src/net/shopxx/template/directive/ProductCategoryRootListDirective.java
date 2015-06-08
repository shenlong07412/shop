/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.entity.ProductCategory;
import net.shopxx.service.ProductCategoryService;
import net.shopxx.service.SensitivityService;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 顶级商品分类列表
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Component("productCategoryRootListDirective")
public class ProductCategoryRootListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "productCategories";

	@Resource(name = "productCategoryServiceImpl")
	private ProductCategoryService productCategoryService;
	@Resource(name = "sensitivityServiceImpl")
	private SensitivityService sensitivityService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<ProductCategory> productCategories =new ArrayList<ProductCategory>();
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		List<ProductCategory> list;
		if (useCache) {
			list = productCategoryService.findRoots(count, cacheRegion);
		} else {
			list = productCategoryService.findRoots(count);
		}
		if(list!=null && list.size()>0){
			for (ProductCategory productCategory : list) {
				String name=sensitivityService.replaceSensitivity(productCategory.getName(), "**");
				productCategory.setName(name);
				String seoTitle=sensitivityService.replaceSensitivity(productCategory.getSeoTitle(), "**");
				productCategory.setSeoTitle(seoTitle);
				String seoKeywords=sensitivityService.replaceSensitivity(productCategory.getSeoKeywords(), "**");
				productCategory.setSeoKeywords(seoKeywords);	
				String seoDescription=sensitivityService.replaceSensitivity(productCategory.getSeoDescription(), "**");
				productCategory.setSeoDescription(seoDescription);				
				productCategories.add(productCategory);
			}
		}
		setLocalVariable(VARIABLE_NAME, productCategories, env, body);
	}

}