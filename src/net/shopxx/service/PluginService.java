/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service;

import java.util.List;

import net.shopxx.plugin.OAuthPlugin;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.plugin.StoragePlugin;

/**
 * Service - 插件
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface PluginService {

    /**
     * 获取支付插件
     * 
     * @return 支付插件
     */
    List<PaymentPlugin> getPaymentPlugins();

    /**
     * 获取第三方登录插件
     * 
     * @return 登录插件
     */
    List<OAuthPlugin> getOAuthPlugins();

    /**
     * 获取存储插件
     * 
     * @return 存储插件
     */
    List<StoragePlugin> getStoragePlugins();

    /**
     * 获取支付插件
     * 
     * @param isEnabled 是否启用
     * @return 支付插件
     */
    List<PaymentPlugin> getPaymentPlugins(boolean isEnabled);

    /**
     * 获取存储插件
     * 
     * @param isEnabled 是否启用
     * @return 存储插件
     */
    List<StoragePlugin> getStoragePlugins(boolean isEnabled);

    /**
     * 获取登录插件
     * 
     * @param isEnabled 是否启用
     * @return 登录插件
     */
    List<OAuthPlugin> getOAuthPlugins(boolean isEnabled);

    /**
     * 获取支付插件
     * 
     * @param id ID
     * @return 支付插件
     */
    PaymentPlugin getPaymentPlugin(String id);

    /**
     * 获取存储插件
     * 
     * @param id ID
     * @return 存储插件
     */
    StoragePlugin getStoragePlugin(String id);

    /**
     * 获取登录插件
     * 
     * @param id ID
     * @return 登录插件
     */
    OAuthPlugin getOAuthPlugin(String id);

}
