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

package net.shopxx.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Token 帮助类
 * 
 * @author L.cm email: 596392912@qq.com site: http://www.dreamlu.net
 * @date Jun 24, 2013 9:58:25 PM
 */
public class TokenUtil {

    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 参考自 qq sdk
     * 
     * @param @param string
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getAccessToken(String string) {
        String accessToken = "";
        try {
            JSONObject json = JSONObject.parseObject(string);
            if (null != json) {
                accessToken = json.getString("access_token");
            }
        } catch (Exception e) {
            Matcher m = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$").matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                Matcher m2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$").matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                }
            }
        }
        return accessToken;
    }

    /**
     * 匹配openid
     * 
     * @param @param string
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getOpenId(String string) {
        String openid = null;
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(string);
        if (m.find()) openid = m.group(1);
        return openid;
    }

    /**
     * sina uid于qq分离
     * 
     * @Title: getUid
     * @param @param string
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getUid(String string) {
        JSONObject json = JSONObject.parseObject(string);
        return json.getString("uid");
    }

    /**
     * 生成一个随机数
     * 
     * @return
     */
    public static String randomState() {
        return RandomStringUtils.random(24, STR_S);
    }
}