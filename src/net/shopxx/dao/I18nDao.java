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
// Created on 2014年7月16日
// $Id$

package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.I18n;
import net.shopxx.entity.biz.Merchant;

/**
 * @author czllfy
 */
public interface I18nDao extends BaseDao<I18n, Long> {

    /**取出全部的国际化代码
     * @return
     */
    public List<I18n> findAll();
    /**
	 * 判断i18nCode是否存在
	 * 
	 * @param i18nCode
	 *            国际化代码(忽略大小写)
	 * @return 国际化代码是否存在
	 */
	boolean i18nCodeExists(String i18nCode);

	/**
	 * 根据i18nCode查找国际化代码
	 * 
	 * @param i18nCode
	 *            国际化代码(忽略大小写)
	 * @return 国际化代码，若不存在则返回null
	 */
	public I18n findByi18nCode(String i18nCode);

}
