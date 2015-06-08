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
 * Entity - 社区和工作人员关系表
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_communitytostaff")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_communitytostaff__sequence")
public class Communitytostaff extends OrderEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3330973729180038997L;

    private Community   community;//小区
    
    private String posistion;//职位
    
    private PropertyStaff propertyStaff;//物业人员

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Community getCommunity() {
        return community;
    }

    
    public void setCommunity(Community community) {
        this.community = community;
    }

    
    public String getPosistion() {
        return posistion;
    }

    
    public void setPosistion(String posistion) {
        this.posistion = posistion;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public PropertyStaff getPropertyStaff() {
        return propertyStaff;
    }

    
    public void setPropertyStaff(PropertyStaff propertyStaff) {
        this.propertyStaff = propertyStaff;
    }

    
    
}