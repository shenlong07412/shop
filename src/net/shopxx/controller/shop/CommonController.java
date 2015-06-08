/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.shop;

import java.awt.image.BufferedImage;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shopxx.Setting;
import net.shopxx.entity.Area;
import net.shopxx.entity.Community;
import net.shopxx.service.AreaService;
import net.shopxx.service.CaptchaService;
import net.shopxx.service.RSAService;
import net.shopxx.util.SettingUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller - 共用
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("shopCommonController")
@RequestMapping("/common")
public class CommonController {

    @Resource(name = "rsaServiceImpl")
    private RSAService     rsaService;
    @Resource(name = "areaServiceImpl")
    private AreaService    areaService;
    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    /**
     * 网站关闭
     */
    @RequestMapping("/site_close")
    public String siteClose() {
        Setting setting = SettingUtils.get();
        if (setting.getIsSiteEnabled()) {
            return "redirect:/";
        } else {
            return "/shop/common/site_close";
        }
    }

    /**
     * 公钥
     */
    @RequestMapping(value = "/public_key", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> publicKey(HttpServletRequest request) {
        RSAPublicKey publicKey = rsaService.generateKey(request);
        Map<String, String> data = new HashMap<String, String>();
        data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
        data.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
        return data;
    }

    /**
     * 地区
     */
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> area(String parentId) {
        List<Area> areas = new ArrayList<Area>();
        Set<Community> cSet = new HashSet<Community>();
        Map<String, String> options = new HashMap<String, String>();
        if (parentId != null && !"".equals(parentId)) {
            String[] areaArry = parentId.split(";");
            if (areaArry != null && areaArry.length > 0) {
                if ("1".equals(areaArry[0])) {
                    Area parent = areaService.find(Long.parseLong(areaArry[1]));
                    if (parent != null) {
                        if (parent.getChildren().isEmpty()) {
                            cSet = parent.getCommunities();
                        } else {
                            areas = new ArrayList<Area>(parent.getChildren());
                        }
                    }
                }
            }
        } else {
            areas = areaService.findRoots();
        }
        for (Area area : areas) {
            options.put("1;" + area.getId().toString(), area.getName());
        }
        for (Community community : cSet) {
            options.put("2;" + community.getId().toString(), community.getName());
        }
        return options;
    }

    /**
     * 验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(captchaId)) {
            captchaId = request.getSession().getId();
        }
        String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
        String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
        response.addHeader(pragma, value);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            BufferedImage bufferedImage = captchaService.buildImage(captchaId);
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }

    /**
     * 错误提示
     */
    @RequestMapping("/error")
    public String error() {
        return "/shop/common/error";
    }

    /**
     * 资源不存在
     */
    @RequestMapping("/resource_not_found")
    public String resourceNotFound() {
        return "/shop/common/resource_not_found";
    }

}
