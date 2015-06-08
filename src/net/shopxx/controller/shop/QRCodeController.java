/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.controller.shop;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shopxx.service.QRCodeService;
import net.shopxx.util.QRCodeUtil;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

/**
 * Controller - 投诉报修
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("shopQRCodeController")
@RequestMapping("/QRCode")
public class QRCodeController extends BaseController {
	@Resource(name = "qRCodeServiceImpl")
	private QRCodeService qRCodeService;
	
	/**
	 * 输入 信息还有 ，二维码中间图片地址 返回二维码地址
	 * @throws Exception 
	 */
	@RequestMapping(value = "/encode", method = RequestMethod.GET)
	@ResponseBody
	public String encode(String text,String ico, ModelMap model) throws Exception {
		return qRCodeService.encodeIcoUrl(text, ico, true, 0, 0);
	}

	
	/**
     * 二维码传入为base64传出参数加密  ，参数请加密成 base64
     */
    @RequestMapping(value = "/encodeBase64", method = RequestMethod.GET)
    public void image(String text,String ico, HttpServletRequest request, HttpServletResponse response) throws Exception {    	
    	
    	text= new String(new BASE64Decoder().decodeBuffer(text), "utf-8");
    	String[] splits=text.split("\\?");
    	
    	text=splits[0]+"?parameter="+Base64.encodeBase64String(splits[1].getBytes());
    	
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = response.getOutputStream();
            BufferedImage bufferedImage =QRCodeUtil.createImageInUrl(text, ico, true, 0, 0);
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }
}