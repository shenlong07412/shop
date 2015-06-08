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

package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 国际化项目实体
 * 
 * @author czllfy
 */

@Entity
@Table(name = "xx_sys_i18n_item")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_sys_i18n_item_sequence")
public class I18nItem extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 714564307936017903L;
    private I18n              i18n;
    private String            locale;
    private String            msg;

    /**
     * @return the i18n
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i18n_id")
    public I18n getI18n() {
        return i18n;
    }

    /**
     * @param i18n the i18n to set
     */
    public void setI18n(I18n i18n) {
        this.i18n = i18n;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
