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
// Created on 2014年7月10日
// $Id$

package net.shopxx.shiro.realm;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Setting.CaptchaType;
import net.shopxx.entity.biz.Account;
import net.shopxx.service.AccountService;
import net.shopxx.service.CaptchaService;
import net.shopxx.service.RoleService;
import net.shopxx.shiro.BizAuthenticationToken;
import net.shopxx.shiro.Principal;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author czllfy
 */
public class BizAuthenticationRealm extends AuthorizingRealm {

    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;
    @Resource(name = "accountServiceImpl")
    private AccountService accountService;

    @Value("${dev_model}")
    private Boolean        isDevModel;

    /**
     * 获取认证信息
     * 
     * @param token 令牌
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
        BizAuthenticationToken authenticationToken = (BizAuthenticationToken) token;
        String username = authenticationToken.getUsername();
        String password = new String(authenticationToken.getPassword());
        String captchaId = authenticationToken.getCaptchaId();
        String captcha = authenticationToken.getCaptcha();
        String ip = authenticationToken.getHost();
        if (!isDevModel && !captchaService.isValid(CaptchaType.adminLogin, captchaId, captcha)) {
            throw new UnsupportedTokenException();
        }
        if (username != null && password != null) {
            Account admin = accountService.findByUsername(username);
            if (admin == null) {
                throw new UnknownAccountException();
            }
            SecurityUtils.getSubject().getSession().setAttribute("userType", "biz");
            return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
        }
        throw new UnknownAccountException();
    }

    /**
     * 获取授权信息
     * 
     * @param principals principals
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Collection realms = principals.fromRealm(getName());
        if (realms != null && realms.size() > 0) {
            Principal principal = (Principal) realms.iterator().next();
            if (principal != null) {
                List<String> authorities = accountService.findAuthorities(principal.getId());
                if (authorities != null) {
                    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                    authorizationInfo.addStringPermissions(authorities);
                    return authorizationInfo;
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.apache.shiro.realm.AuthenticatingRealm#supports(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    public boolean supports(org.apache.shiro.authc.AuthenticationToken token) {
        return token instanceof BizAuthenticationToken ? true : false;
    }
}
