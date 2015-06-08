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

package net.shopxx.controller.shop;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.shopxx.entity.Cart;
import net.shopxx.entity.Member;
import net.shopxx.entity.MemberOAuth;
import net.shopxx.plugin.oauth.OAuthBaiduPlugin;
import net.shopxx.plugin.oauth.OAuthQQPlugin;
import net.shopxx.plugin.oauth.OAuthSinaPlugin;
import net.shopxx.service.CartService;
import net.shopxx.service.MemberOAuthService;
import net.shopxx.service.MemberRankService;
import net.shopxx.service.MemberService;
import net.shopxx.shiro.Principal;
import net.shopxx.util.TokenUtil;
import net.shopxx.util.WebUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

/**
 * @author czllfy
 */
@Controller("shopOAuthController")
@RequestMapping("/oauth")
public class OAuthController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

    @Resource(name = "qqOAuthPlugin")
    private OAuthQQPlugin       qq_oauthPlugin;
    @Resource(name = "oAuthSinaPlugin")
    private OAuthSinaPlugin     sina_oauthPlugin;
    @Resource(name = "oAuthBaiduPlugin")
    private OAuthBaiduPlugin    baidu_oauthPlugin;
    @Resource(name = "memberServiceImpl")
    private MemberService       memberService;
    @Resource(name = "memberOAuthServiceImpl")
    private MemberOAuthService   memberOAuthService;   
    @Resource(name = "memberRankServiceImpl")
    private MemberRankService      memberRankService;
    @Resource(name = "cartServiceImpl")
    private CartService    cartService;

    /**
     * 跳转至QQ的登录界面
     * 
     * @return
     */
    @RequestMapping("/qq/login")
    public String qq_login() {
        try {
            String state = TokenUtil.randomState();
            return "redirect:" + qq_oauthPlugin.getAuthorizeUrl(state);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "redirect:/";
        }
    }

    /**
     * QQ登录后的回调界面
     * 
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/qq/callback")
    public String qq_callback(HttpServletRequest request,HttpServletResponse response, HttpSession session,String code, String state) {
    	JSONObject object;
		try {
			object = qq_oauthPlugin.getUserInfoByCode(code);
			return this.qqOAuthInfo(request, response, session,object,"qqOAuthPlugin");
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
    }

    /**
     * QQ绑定界面
     * 
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/qq/bind")
    public String qq_bind(String code, String state) {
        return "";
    }

    /**
     * 跳转至sina的登录界面
     * 
     * @return
     */
    @RequestMapping("/sina/login")
    public String sina_login() {
        try {
            String state = TokenUtil.randomState();
            return "redirect:" + sina_oauthPlugin.getAuthorizeUrl(state);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "redirect:/";
        }
    }

    /**
     * sina登录后的回调界面
     * 
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/sina/callback")
    public String sina_callback(HttpServletRequest request,HttpServletResponse response, HttpSession session,String code, String state)
    {
    	JSONObject object;
		try {
			object = sina_oauthPlugin.getUserInfoByCode(code);
			return this.sinaOAuthInfo(request, response, session,object,"oAuthSinaPlugin");
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "redirect:/";
    }

    /**
     * QQ绑定界面
     * 
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/sina/bind")
    public String sina_bind(String code, String state) {
        return "";
    }

    /**
     * 跳转至baidu的登录界面
     * 
     * @return
     */
    @RequestMapping("/baidu/login")
    public String baidu_login() {
        try {
            String state = TokenUtil.randomState();
            return "redirect:" + baidu_oauthPlugin.getAuthorizeUrl(state);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "redirect:/";
        }
    }

    /**
     * baidu登录后的回调界面
     * 
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/baidu/callback")
    public String baidu_callback(HttpServletRequest request,HttpServletResponse response, HttpSession session,String code, String state) {
    	JSONObject object;
		try {
			object = baidu_oauthPlugin.getUserInfoByCode(code);
			return this.baiduOAuthInfo(request, response, session,object,"oAuthBaiduPlugin");
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "redirect:/";    	
    }

    /**
     * QQ绑定界面
     * 
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/baidu/bind")
    public String baidu_bind(String code, String state) {
        return "";
    }
    
    /**
     * 验证授权信息 qq
     * @param request
     * @param response
     * @param session
     * @param code
     * @param state
     * @return
     */
    private String qqOAuthInfo(HttpServletRequest request,HttpServletResponse response, HttpSession session,JSONObject object,
    		String oauthName){
			String ret=object.getString("ret");
		if(StringUtils.isNotBlank(ret) && "0".equals(ret)){
			//根据access_token查找MemberOauth是否存在，若存在,获取member信息，反之则插入用户数据以及授权
			String access_token=object.getString("access_token");
			MemberOAuth oAuth=memberOAuthService.getInfoByToken(access_token);
			Member member;
			if(oAuth!=null){
				member=oAuth.getMember();
			}else{
				member = new Member();
				String openid=object.getString("openid");
				member.setUsername(openid);
				member.setActivateEmail(true);
				String nickname =object.getString("nickname");
				member.setName(nickname);
				//初始化非空数据
				member.setAmount(new BigDecimal(0));
				member.setBalance(new BigDecimal(0));
				member.setEmail(openid);
				member.setIsEnabled(true);
				member.setIsLocked(true);
				member.setLoginFailureCount(0);
				member.setPassword(openid);
				member.setPoint(0l);
				member.setRegisterIp(request.getRemoteAddr());
				member.setMemberRank(memberRankService.findDefault());
				memberService.save(member);
				this.saveMemberOAuth(new MemberOAuth(), member, access_token,oauthName);
			}
			//保存session信息
			this.saveSession(session, request, response, member);
			//跳转登入
			return "redirect:/";
		}
        return "/login.jhtml";
    }
    
    /**
     * 验证授权信息 qq
     * @param request
     * @param response
     * @param session
     * @param code
     * @param state
     * @return
     */
    private String sinaOAuthInfo(HttpServletRequest request,HttpServletResponse response, HttpSession session,JSONObject object,
    		String oauthName){
			String ret=object.getString("error_code");
		if(!StringUtils.isNotBlank(ret)){
			//根据access_token查找MemberOauth是否存在，若存在,获取member信息，反之则插入用户数据以及授权
			String access_token=object.getString("access_token");
			MemberOAuth oAuth=memberOAuthService.getInfoByToken(access_token);
			Member member;
			if(oAuth!=null){
				member=oAuth.getMember();
			}else{
				member = new Member();
				String id=object.getString("id");
				member.setUsername(id);
				member.setActivateEmail(true);
				String screen_name =object.getString("screen_name");
				member.setName(screen_name);
				//初始化非空数据
				member.setAmount(new BigDecimal(0));
				member.setBalance(new BigDecimal(0));
				member.setEmail(id);
				member.setIsEnabled(true);
				member.setIsLocked(true);
				member.setLoginFailureCount(0);
				member.setPassword(id);
				member.setPoint(0l);
				member.setRegisterIp(request.getRemoteAddr());
				member.setMemberRank(memberRankService.findDefault());
				memberService.save(member);
				this.saveMemberOAuth(new MemberOAuth(), member, access_token,oauthName);
			}
			//保存session信息
			this.saveSession(session, request, response, member);
			//跳转登入
			return "redirect:/";
		}
        return "/login.jhtml";
    }    
 
    /**
     * 验证授权信息 qq
     * @param request
     * @param response
     * @param session
     * @param code
     * @param state
     * @return
     */
    private String baiduOAuthInfo(HttpServletRequest request,HttpServletResponse response, HttpSession session,JSONObject object,
    		String oauthName){
			String ret=object.getString("error_code");
		if(!StringUtils.isNotBlank(ret)){
			//由于access_token会变化，所以采用userid唯一标识查找MemberOauth是否存在，若存在,获取member信息，反之则插入用户数据以及授权
			String userid=object.getString("userid");
			MemberOAuth oAuth=memberOAuthService.getInfoByToken(userid);
			Member member;
			if(oAuth!=null){
				member=oAuth.getMember();
			}else{
				member = new Member();
				member.setUsername(userid);
				member.setActivateEmail(true);
				String username =object.getString("username");
				member.setName(username);
				//初始化非空数据
				member.setAmount(new BigDecimal(0));
				member.setBalance(new BigDecimal(0));
				member.setEmail(userid);
				member.setIsEnabled(true);
				member.setIsLocked(true);
				member.setLoginFailureCount(0);
				member.setPassword(userid);
				member.setPoint(0l);
				member.setRegisterIp(request.getRemoteAddr());
				member.setMemberRank(memberRankService.findDefault());
				memberService.save(member);
				this.saveMemberOAuth(new MemberOAuth(), member, userid,oauthName);
			}
			//保存session信息
			this.saveSession(session, request, response, member);
			//跳转登入
			return "redirect:/";
		}
        return "/login.jhtml";
    }    
    
    /**
     * 保存授权信息
     * @param oAuth
     * @param member
     * @param access_token
     */
    private void saveMemberOAuth(MemberOAuth oAuth,Member member,String access_token,String oauthName){
		oAuth = new MemberOAuth();
		oAuth.setMember(member);
		oAuth.setToken(access_token);
		oAuth.setOauthName(oauthName);
		memberOAuthService.save(oAuth);
    }
    
    /**
     * 登入后存储session信息
     * @param session
     * @param request
     * @param response
     * @param member
     */
    private void saveSession(HttpSession session,HttpServletRequest request,
            HttpServletResponse response,Member member){
        Cart cart = cartService.getCurrent();
        if (cart != null) {
            if (cart.getMember() == null) {
                cartService.merge(member, cart);
                WebUtils.removeCookie(request, response, Cart.ID_COOKIE_NAME);
                WebUtils.removeCookie(request, response, Cart.KEY_COOKIE_NAME);
            }
        }
        Map<String, Object> attributes = new HashMap<String, Object>();
        Enumeration<?> keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            attributes.put(key, session.getAttribute(key));
        }
        session.invalidate();
        session = request.getSession();
        for (Entry<String, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername()));
        WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());
    }
}
