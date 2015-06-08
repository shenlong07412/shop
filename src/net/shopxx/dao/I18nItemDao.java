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

import net.shopxx.entity.I18nItem;

/**
 * @author czllfy
 */
public interface I18nItemDao extends BaseDao<I18nItem, Long> {
	
	public List<I18nItem> getListByI18nCode(String i18nCode);
	
	public  I18nItem  getByLocalCode(String localCode);
	
	public boolean localCodeExists(String localCode);
}
