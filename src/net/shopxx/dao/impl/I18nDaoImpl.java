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
import net.shopxx.dao.I18nDao;
import net.shopxx.entity.I18n;
import org.springframework.stereotype.Repository;

/**
 * @author czllfy
 */
@Repository("i18nDaoImpl")
public class I18nDaoImpl extends BaseDaoImpl<I18n, Long> implements I18nDao {

    public List<I18n> findAll() {
        try {
            String jpql = "select i18n from I18n i18n where i18n.dataflg=1";
            return entityManager.createQuery(jpql, I18n.class).setFlushMode(FlushModeType.COMMIT).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

	@Override
	public boolean i18nCodeExists(String i18nCode) {
		if (i18nCode == null) {
			return false;
		}
		String jpql = "select count(*) from I18n i18n where lower(i18n.code) = lower(:code)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("code", i18nCode).getSingleResult();
		return count > 0;
	}

	@Override
	public I18n findByi18nCode(String i18nCode) {
		if (i18nCode == null) {
			return null;
		}
		try {
			String jpql = "select i18n from I18n i18n where lower(i18n.code) = lower(:code)";
			return entityManager.createQuery(jpql, I18n.class).setFlushMode(FlushModeType.COMMIT).setParameter("code", i18nCode).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
