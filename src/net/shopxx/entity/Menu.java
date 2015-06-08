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
// Created on 2014年6月26日
// $Id$

package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author czllfy
 */

@Entity
@Table(name = "xx_sys_menu")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_sys_menu_sequence")
public class Menu extends OrderEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -4846720622828192587L;

    private String            name;

    private String            i18Code;

    private String            code;
    
    private String            detailCode;

    private Menu              parent;

    private Set<Menu>         children         = new HashSet<Menu>();

    private String            accessUrl;

    private String            icon;

    private String            className;

    private int               level;

    /**
     * @return the name
     */
    @JsonProperty
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the i18Code
     */
    public String getI18Code() {
        return i18Code;
    }

    /**
     * @param i18Code the i18Code to set
     */
    public void setI18Code(String i18Code) {
        this.i18Code = i18Code;
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
     * 获取上级地区
     * 
     * @return 上级地区
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Menu getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Menu parent) {
        this.parent = parent;
    }

    /**
     * 获取下级分类
     * 
     * @return 下级分类
     */
    @JsonProperty
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy("order asc")
    public Set<Menu> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

    /**
     * @return the url
     */
    public String getAccessUrl() {
        return accessUrl;
    }

    /**
     * @param url the url to set
     */
    public void setAccessUrl(String url) {
        this.accessUrl = url;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    
    public String getDetailCode() {
        return detailCode;
    }

    
    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

}
