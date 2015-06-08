/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import net.shopxx.Setting;
import net.shopxx.plugin.StoragePlugin;
import net.shopxx.service.PluginService;
import net.shopxx.service.QRCodeService;
import net.shopxx.util.FreemarkerUtils;
import net.shopxx.util.QRCodeUtil;
import net.shopxx.util.SettingUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

/**
 * Service - 文件
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("qRCodeServiceImpl")
public class QRCodeServiceImpl implements QRCodeService,ServletContextAware{
	/** servletContext */
	private ServletContext servletContext;

	@Resource(name = "pluginServiceImpl")
	private PluginService pluginService;
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	public String encode(String content, String imgPath,boolean needCompress, int fg, int bg) {
		try {
			//获取编码图片
			BufferedImage image=QRCodeUtil.createImage(content, imgPath,
					needCompress,fg,bg);
			
			//获取设置的二维码保存路径
			Setting setting = SettingUtils.get();
			String destPath=setting.getQRCodePath();			
			Map<String, Object> model = new HashMap<String, Object>();
			destPath = FreemarkerUtils.process(destPath, model);
			
			//获取文件夹真实路径
			String root= servletContext.getRealPath("").replace("\\", "/");
			String realFolder= root+destPath;
			
			//创建二维码图片，不过不存在文件夹先创建文件夹
			File files=new File(realFolder);
			if(!files.exists()){
				files.mkdirs();
			}			
			QRCodeUtil.mkdirs(destPath);
			String file = new Random().nextInt(99999999)+".jpg";
			ImageIO.write(image, "JPG", new File(realFolder+"/"+file));
			
			
			for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)){
				destPath= storagePlugin.getUrl(destPath);
				return destPath+file;
			}	
			//QRCodeUtil.encode(content,imgPath,destPath,needCompress,fg,bg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String encodeIcoUrl(String content, String ico,
			boolean needCompress, int fg, int bg) {
		try {
			//获取编码图片
			BufferedImage image=QRCodeUtil.createImageInUrl(content, ico, needCompress, fg, bg);		
			//获取设置的二维码保存路径
			Setting setting = SettingUtils.get();
			String destPath=setting.getQRCodePath();			
			Map<String, Object> model = new HashMap<String, Object>();
			destPath = FreemarkerUtils.process(destPath, model);
			
			//获取文件夹真实路径
			String root= servletContext.getRealPath("").replace("\\", "/");
			String realFolder= root+destPath;
			
			//创建二维码图片，不过不存在文件夹先创建文件夹
			File files=new File(realFolder);
			if(!files.exists()){
				files.mkdirs();
			}			
			QRCodeUtil.mkdirs(destPath);
			String file = new Random().nextInt(99999999)+".jpg";
			ImageIO.write(image, "JPG", new File(realFolder+"/"+file));
						
			for (StoragePlugin storagePlugin : pluginService.getStoragePlugins(true)){
				destPath= storagePlugin.getUrl(destPath);
				return destPath+file;
			}	
			//QRCodeUtil.encode(content,imgPath,destPath,needCompress,fg,bg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}