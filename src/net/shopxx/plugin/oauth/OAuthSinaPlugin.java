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
// Created on 2014年7月21日
// $Id$

package net.shopxx.plugin.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import net.shopxx.entity.PluginConfig;
import net.shopxx.plugin.OAuthPlugin;
import net.shopxx.util.TokenUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author czllfy
 */
@Component("oAuthSinaPlugin")
public class OAuthSinaPlugin extends OAuthPlugin {

    private static final Logger logger         = LoggerFactory.getLogger(OAuthSinaPlugin.class);

    private static final String AUTH_URL       = "https://api.weibo.com/oauth2/authorize";
    private static final String TOKEN_URL      = "https://api.weibo.com/oauth2/access_token";
    private static final String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
    private static final String USER_INFO_URL  = "https://api.weibo.com/2/users/show.json";

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getName()
     */
    @Override
    public String getName() {
        return "新浪微博";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getVersion()
     */
    @Override
    public String getVersion() {
        return "1.0";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getAuthor()
     */
    @Override
    public String getAuthor() {
        return "chenzhl";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getSiteUrl()
     */
    @Override
    public String getSiteUrl() {
        return "";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getInstallUrl()
     */
    @Override
    public String getInstallUrl() {
        return "sina_oauth/install.jhtml";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getUninstallUrl()
     */
    @Override
    public String getUninstallUrl() {
        return "sina_oauth/uninstall.jhtml";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#getSettingUrl()
     */
    @Override
    public String getSettingUrl() {
        return "sina_oauth/setting.jhtml";
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#bind()
     */
    @Override
    public boolean bind() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#unbind()
     */
    @Override
    public boolean unbind() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.plugin.OAuthPlugin#login()
     */
    @Override
    public void login() {

    }

    /**
     * @throws UnsupportedEncodingException 获取授权url DOC：http://open.weibo.com/wiki/Oauth2/authorize
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException {
        PluginConfig pluginConfig = getPluginConfig();
        Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", pluginConfig.getAttribute("openid"));
        params.put("redirect_uri", pluginConfig.getAttribute("redirectUri"));
        if (StringUtils.isNotBlank(state)) {
            params.put("state", state); // OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException 获取token
     * @param @param code
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException,
                                             NoSuchProviderException {
        PluginConfig pluginConfig = getPluginConfig();
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("client_id", pluginConfig.getAttribute("openid"));
        params.put("client_secret", pluginConfig.getAttribute("openkey"));
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", pluginConfig.getAttribute("redirectUri"));
        String token = TokenUtil.getAccessToken(super.doPost(TOKEN_URL, params));
        logger.debug(token);
        return token;
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException 获取TokenInfo
     * @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String getTokenInfo(String accessToken) throws IOException, KeyManagementException,
                                                  NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        String openid = TokenUtil.getUid(super.doPost(TOKEN_INFO_URL, params));
        logger.debug(openid);
        return openid;
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException 获取用户信息 DOC：http://open.weibo.com/wiki/2/users/show
     * @param accessToken
     * @param uid
     * @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public String getUserInfo(String accessToken, String uid) throws IOException, KeyManagementException,
                                                             NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("uid", uid);
        params.put("access_token", accessToken);
        String userInfo = super.doGet(USER_INFO_URL, params);
        logger.debug(userInfo);
        return userInfo;
    }

    /**
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException 根据code一步获取用户信息
     * @param @param args 设定文件
     * @return void 返回类型
     * @throws
     */
    public JSONObject getUserInfoByCode(String code) throws IOException, KeyManagementException,
                                                    NoSuchAlgorithmException, NoSuchProviderException {
        String accessToken = getTokenByCode(code);
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        String uid = getTokenInfo(accessToken);
        if (StringUtils.isBlank(uid)) {
            return null;
        }
        JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, uid));
        dataMap.put("access_token", accessToken);
        logger.debug(dataMap.toJSONString());
        return dataMap;
    }
}
