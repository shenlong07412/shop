/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.BaseEntity;

/**
 * 日志配置
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_estate_log_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_log_config_sequence")
public class LogConfig extends BaseEntity {

	private static final long serialVersionUID = -1108848647938408402L;

	/** 操作名称 */
	private String operation;

	/** 请求URL */
	private String urlPattern;

	/**
	 * 获取操作名称
	 * 
	 * @return 操作名称
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * 设置操作名称
	 * 
	 * @param operation
	 *            操作名称
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 获取请求URL
	 * 
	 * @return 请求URL
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * 设置请求URL
	 * 
	 * @param urlPattern
	 *            请求URL
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

}