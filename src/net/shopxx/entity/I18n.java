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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 国际化实体
 * 
 * @author czllfy
 */
@Entity
@Table(name = "xx_sys_i18n")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_sys_i18n_sequence")
public class I18n extends BaseEntity {

    /**
     * 
     */
    private static final long     serialVersionUID = 1759794980856803175L;

    private String                code;

    private Map<String, I18nItem> msg              = new HashMap<String, I18nItem>();
    
    private String 				 memo;		//国际化编码备注，通常取zh_cn编码
    
    private String 				category;	//系统范畴

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    @OneToMany(mappedBy = "i18n", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapKey(name = "locale")
    public Map<String, I18nItem> getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(Map<String, I18nItem> msg) {
        this.msg = msg;
    }

}
