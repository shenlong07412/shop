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

package net.shopxx.spring;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.Transient;

import net.shopxx.dao.I18nDao;
import net.shopxx.dao.I18nItemDao;
import net.shopxx.entity.I18n;
import net.shopxx.entity.I18nItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.io.Files;

/**
 * @author czllfy
 */
public class DatabaseMessageSource extends AbstractMessageSource {

    private static final Logger           logger   = LoggerFactory.getLogger(DatabaseMessageSource.class);

    private Table<String, String, String> messages = HashBasedTable.create();

    @Resource(name = "i18nDaoImpl")
    private I18nDao                       i18nDao;

    @Resource(name = "i18nItemDaoImpl")
    private I18nItemDao                   i18nItemDao;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = messages.get(code, locale.toString());
        return createMessageFormat(msg, locale);

    }

    @PostConstruct
    public void load() {
        logger.debug("从烽据库加载I18N配置");
        List<I18n> list = i18nDao.findAll();
        if (list != null && list.size() > 0) {
            for (Iterator<I18n> iterator = list.iterator(); iterator.hasNext();) {
                I18n i18n = (I18n) iterator.next();
                if (i18n.getMsg() != null && i18n.getMsg().size() > 0) {
                    Iterator<Entry<String, I18nItem>> msgIter = i18n.getMsg().entrySet().iterator();
                    while (msgIter.hasNext()) {
                        Entry<String, I18nItem> current = msgIter.next();
                        messages.put(i18n.getCode(), current.getKey(), current.getValue().getMsg());
                    }
                }

            }
        }
    }

    public DatabaseMessageSource clearCache() {
        messages.clear();
        return this;
    }
}
