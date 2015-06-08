/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service;


/**
 * Service - 文件
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public interface QRCodeService {
	
	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址  计算机路径  可为空
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @param  fg
	 * 			  前景色  默认黑
	 *  @param  bg 默认白
	 * 			背景色
	 * @throws Exception
	 */
	String encode(String content, String imgPath,boolean needCompress,int fg,int bg);
	
	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param imgPath
	 *            LOGO地址 url路径   可为空
	 * @param destPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @param  fg
	 * 			  前景色  默认黑
	 *  @param  bg 默认白
	 * 			背景色
	 * @throws Exception
	 */
	String encodeIcoUrl(String content, String ico,boolean needCompress,int fg,int bg);
}