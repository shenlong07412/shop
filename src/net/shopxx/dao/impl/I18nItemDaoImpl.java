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

package net.shopxx.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import net.shopxx.dao.I18nItemDao;
import net.shopxx.entity.I18nItem;

/**
 * @author czllfy
 */
@Repository("i18nItemDaoImpl")
public class I18nItemDaoImpl extends BaseDaoImpl<I18nItem, Long> implements I18nItemDao {

	@Override
	public List<I18nItem> getListByI18nCode(String i18nCode) {
		if (i18nCode == null) {
			return null;
		}
		String jpql = "select i18nItem from I18nItem i18nItem where i18nItem.i18n.id in (select i18n.id from I18n i18n where lower(i18n.code) = lower(:code))";
		try {
			return entityManager.createQuery(jpql, I18nItem.class)
					.setFlushMode(FlushModeType.COMMIT)
					.setParameter("code", i18nCode).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public I18nItem getByLocalCode(String localCode) {
		// TODO Auto-generated method stub
		if (localCode == null) {
            return null;
        }
        String jpql = "select i18nItem from I18nItem i18nItem where i18nItem.localCode =  lower(:code))";
        try {
            return entityManager.createQuery(jpql, I18nItem.class).setFlushMode(FlushModeType.COMMIT).setParameter("code", localCode).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	public boolean localCodeExists(String localCode) {
		if (localCode == null) {
			return false;
		}
		String jpql = "select count(*) from I18nItem i18nItem where lower(i18nItem.localCode) = lower(:localCode)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("localCode", localCode).getSingleResult();
		return count > 0;
	}

}
