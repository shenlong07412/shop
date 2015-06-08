/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.shopxx.service.CacheService;
import net.shopxx.spring.DatabaseMessageSource;
import net.shopxx.util.SettingUtils;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.TemplateModelException;

/**
 * Service - 缓存
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("cacheServiceImpl")
public class CacheServiceImpl implements CacheService {

    @Resource(name = "ehCacheManager")
    private CacheManager                          cacheManager;
    @Resource(name = "messageSource")
    private DatabaseMessageSource messageSource;
    @Resource(name = "freeMarkerConfigurer")
    private FreeMarkerConfigurer                  freeMarkerConfigurer;

    public String getDiskStorePath() {
        return cacheManager.getConfiguration().getDiskStoreConfiguration().getPath();
    }

    public int getCacheSize() {
        int cacheSize = 0;
        String[] cacheNames = cacheManager.getCacheNames();
        if (cacheNames != null) {
            for (String cacheName : cacheNames) {
                Ehcache cache = cacheManager.getEhcache(cacheName);
                if (cache != null) {
                    cacheSize += cache.getSize();
                }
            }
        }
        return cacheSize;
    }

    @CacheEvict(value = { "setting", "authorization", "logConfig", "template", "shipping", "area", "seo", "adPosition",
            "memberAttribute", "navigation", "tag", "friendLink", "brand", "article", "articleCategory", "product",
            "productCategory", "review", "consultation", "promotion" }, allEntries = true)
    public void clear() {
        //reloadableResourceBundleMessageSource.clearCache().load();
        try {
            freeMarkerConfigurer.getConfiguration().setSharedVariable("setting", SettingUtils.get());
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }
        freeMarkerConfigurer.getConfiguration().clearTemplateCache();
    }

}