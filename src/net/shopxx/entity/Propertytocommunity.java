/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 物业和小区管理关系表
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_propertytocommunity")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_propertytocommunity_sequence")
public class Propertytocommunity extends OrderEntity {

    private static final long serialVersionUID = -3355015304355745640L;

    private PropertyInfo propertyInfo;//物业公司
    
    private Community   community;//小区
    
    private String headPerson;//负责人
    
    private Long isDocking;//是否对接
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public PropertyInfo getPropertyInfo() {
        return propertyInfo;
    }

    
    public void setPropertyInfo(PropertyInfo propertyInfo) {
        this.propertyInfo = propertyInfo;
    }


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Community getCommunity() {
        return community;
    }


    
    public void setCommunity(Community community) {
        this.community = community;
    }


    
    public String getHeadPerson() {
        return headPerson;
    }


    
    public void setHeadPerson(String headPerson) {
        this.headPerson = headPerson;
    }


    
    public Long getIsDocking() {
        return isDocking;
    }


    
    public void setIsDocking(Long isDocking) {
        this.isDocking = isDocking;
    }
    
    

}