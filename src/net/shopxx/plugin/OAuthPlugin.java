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
// Created on 2014年7月8日
// $Id$

package net.shopxx.plugin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import net.shopxx.util.HttpUtils;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

/**
 * oauth第三方登录插件
 * 
 * @author czllfy
 */
public abstract class OAuthPlugin implements Comparable<OAuthPlugin> {

    /** 支付方式名称属性名称 */
    public static final String  OAUTH_NAME_ATTRIBUTE_NAME  = "oAuthName";

    /** LOGO属性名称 */
    public static final String  LOGO_ATTRIBUTE_NAME        = "logo";

    /** 描述属性名称 */
    public static final String  DESCRIPTION_ATTRIBUTE_NAME = "description";

    @Resource(name = "pluginConfigServiceImpl")
    private PluginConfigService pluginConfigService;

    /**
     * 获取ID
     * 
     * @return ID
     */
    public final String getId() {
        return getClass().getAnnotation(Component.class).value();
    }

    /**
     * 获取名称
     * 
     * @return 名称
     */
    public abstract String getName();

    /**
     * 获取版本
     * 
     * @return 版本
     */
    public abstract String getVersion();

    /**
     * 获取作者
     * 
     * @return 作者
     */
    public abstract String getAuthor();

    /**
     * 获取网址
     * 
     * @return 网址
     */
    public abstract String getSiteUrl();

    /**
     * 获取安装URL
     * 
     * @return 安装URL
     */
    public abstract String getInstallUrl();

    /**
     * 获取卸载URL
     * 
     * @return 卸载URL
     */
    public abstract String getUninstallUrl();

    /**
     * 获取设置URL
     * 
     * @return 设置URL
     */
    public abstract String getSettingUrl();

    /**
     * 绑定用户的第三方登录帐户
     * 
     * @return
     */
    public abstract boolean bind();

    /**
     * 解除绑定用户的第三方登录帐户
     * 
     * @return
     */
    public abstract boolean unbind();

    /**
     * 登录
     */
    public abstract void login();

    protected String getAuthorizeUrl(String authorize, Map<String, String> params) throws UnsupportedEncodingException {
        return HttpUtils.initParams(authorize, params);
    }

    protected String doPost(String url, Map<String, String> params) throws IOException, KeyManagementException,
                                                                   NoSuchAlgorithmException, NoSuchProviderException {
        return HttpUtils.post(url, params);
    }

    protected String doGet(String url, Map<String, String> params) throws IOException, KeyManagementException,
                                                                  NoSuchAlgorithmException, NoSuchProviderException {
        return HttpUtils.get(url, params);
    }

    protected String doGetWithHeaders(String url, Map<String, String> headers) throws IOException,
                                                                              KeyManagementException,
                                                                              NoSuchAlgorithmException,
                                                                              NoSuchProviderException {
        return HttpUtils.get(url, null, headers);
    }

    /**
     * 获取是否已安装
     * 
     * @return 是否已安装
     */
    public boolean getIsInstalled() {
        return pluginConfigService.pluginIdExists(getId());
    }

    /**
     * 获取插件配置
     * 
     * @return 插件配置
     */
    public PluginConfig getPluginConfig() {
        return pluginConfigService.findByPluginId(getId());
    }

    /**
     * 获取是否已启用
     * 
     * @return 是否已启用
     */
    public boolean getIsEnabled() {
        PluginConfig pluginConfig = getPluginConfig();
        return pluginConfig != null ? pluginConfig.getIsEnabled() : false;
    }

    /**
     * 获取属性值
     * 
     * @param name 属性名称
     * @return 属性值
     */
    public String getAttribute(String name) {
        PluginConfig pluginConfig = getPluginConfig();
        return pluginConfig != null ? pluginConfig.getAttribute(name) : null;
    }

    /**
     * 获取排序
     * 
     * @return 排序
     */
    public Integer getOrder() {
        PluginConfig pluginConfig = getPluginConfig();
        return pluginConfig != null ? pluginConfig.getOrder() : null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        StoragePlugin other = (StoragePlugin) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }

    public int compareTo(OAuthPlugin storagePlugin) {
        return new CompareToBuilder().append(getOrder(), storagePlugin.getOrder()).append(getId(),
                                                                                          storagePlugin.getId()).toComparison();
    }

}
