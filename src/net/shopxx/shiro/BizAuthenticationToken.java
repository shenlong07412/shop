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
// Created on 2014年7月11日
// $Id$

package net.shopxx.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author czllfy
 */
public class BizAuthenticationToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 5898441540965086534L;

    /** 验证码ID */
    private String            captchaId;

    /** 验证码 */
    private String            captcha;

    /**
     * @param username 用户名
     * @param password 密码
     * @param captchaId 验证码ID
     * @param captcha 验证码
     * @param rememberMe 记住我
     * @param host IP
     */
    public BizAuthenticationToken(String username, String password, String captchaId, String captcha,
                                  boolean rememberMe, String host){
        super(username, password, rememberMe);
        this.captchaId = captchaId;
        this.captcha = captcha;
    }

    /**
     * 获取验证码ID
     * 
     * @return 验证码ID
     */
    public String getCaptchaId() {
        return captchaId;
    }

    /**
     * 设置验证码ID
     * 
     * @param captchaId 验证码ID
     */
    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    /**
     * 获取验证码
     * 
     * @return 验证码
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * 设置验证码
     * 
     * @param captcha 验证码
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
