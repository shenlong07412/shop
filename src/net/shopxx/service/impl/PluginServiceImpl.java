/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.plugin.OAuthPlugin;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.plugin.StoragePlugin;
import net.shopxx.service.PluginService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

/**
 * Service - 插件
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("pluginServiceImpl")
public class PluginServiceImpl implements PluginService {

    @Resource
    private List<PaymentPlugin>        paymentPlugins   = new ArrayList<PaymentPlugin>();
    @Resource
    private List<StoragePlugin>        storagePlugins   = new ArrayList<StoragePlugin>();
    @Resource
    private List<OAuthPlugin>          oAuthPlugins     = new ArrayList<OAuthPlugin>();
    @Resource
    private Map<String, PaymentPlugin> paymentPluginMap = new HashMap<String, PaymentPlugin>();
    @Resource
    private Map<String, StoragePlugin> storagePluginMap = new HashMap<String, StoragePlugin>();

    @Resource
    private Map<String, OAuthPlugin>   oAuthPluginMap   = new HashMap<String, OAuthPlugin>();

    public List<PaymentPlugin> getPaymentPlugins() {
        Collections.sort(paymentPlugins);
        return paymentPlugins;
    }

    public List<StoragePlugin> getStoragePlugins() {
        Collections.sort(storagePlugins);
        return storagePlugins;
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.PluginService#getOAuthPlugins()
     */
    @Override
    public List<OAuthPlugin> getOAuthPlugins() {
        Collections.sort(oAuthPlugins);
        return oAuthPlugins;
    }

    public List<PaymentPlugin> getPaymentPlugins(final boolean isEnabled) {
        List<PaymentPlugin> result = new ArrayList<PaymentPlugin>();
        CollectionUtils.select(paymentPlugins, new Predicate() {

            public boolean evaluate(Object object) {
                PaymentPlugin paymentPlugin = (PaymentPlugin) object;
                return paymentPlugin.getIsEnabled() == isEnabled;
            }
        }, result);
        Collections.sort(result);
        return result;
    }

    public List<StoragePlugin> getStoragePlugins(final boolean isEnabled) {
        List<StoragePlugin> result = new ArrayList<StoragePlugin>();
        CollectionUtils.select(storagePlugins, new Predicate() {

            public boolean evaluate(Object object) {
                StoragePlugin storagePlugin = (StoragePlugin) object;
                return storagePlugin.getIsEnabled() == isEnabled;
            }
        }, result);
        Collections.sort(result);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.PluginService#getOAuthPlugins(boolean)
     */
    public List<OAuthPlugin> getOAuthPlugins(final boolean isEnabled) {
        List<OAuthPlugin> result = new ArrayList<OAuthPlugin>();
        CollectionUtils.select(oAuthPlugins, new Predicate() {

            public boolean evaluate(Object object) {
                OAuthPlugin storagePlugin = (OAuthPlugin) object;
                return storagePlugin.getIsEnabled() == isEnabled;
            }
        }, result);
        Collections.sort(result);
        return result;
    }

    public PaymentPlugin getPaymentPlugin(String id) {
        return paymentPluginMap.get(id);
    }

    public StoragePlugin getStoragePlugin(String id) {
        return storagePluginMap.get(id);
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.PluginService#getOAuthPlugin(java.lang.String)
     */
    public OAuthPlugin getOAuthPlugin(String id) {
        return oAuthPluginMap.get(id);
    }

}
