/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity -  物业公司基础信息
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_property_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_property_info_sequence")
public class PropertyInfo extends OrderEntity {

    private static final long serialVersionUID = -5868409287518808816L;

	private String propertyNo;//物业编号
	
	private String propertyName;//物业名称
	
	private String phone;//物业电话
	
	private String headPerson;//负责人
	
	private String address;//地址
	
	private Long level;//物业等级
	
	private String logo;//logo地址
	
	private String introduction;//简介
	
	private String honor;//荣誉
	
	private Set<PropertyStaff> propertyStaffs = new HashSet<PropertyStaff>();//员工
	
	private Set<Propertytocommunity> propertytocommunitys = new HashSet<Propertytocommunity>();//物业和小区的管理关系

    
    public String getPropertyNo() {
        return propertyNo;
    }

    
    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    
    public String getPropertyName() {
        return propertyName;
    }

    
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getHeadPerson() {
        return headPerson;
    }

    
    public void setHeadPerson(String headPerson) {
        this.headPerson = headPerson;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public Long getLevel() {
        return level;
    }

    
    public void setLevel(Long level) {
        this.level = level;
    }

    
    public String getLogo() {
        return logo;
    }

    
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Lob
    public String getIntroduction() {
        return introduction;
    }

    
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Lob
    public String getHonor() {
        return honor;
    }

    
    public void setHonor(String honor) {
        this.honor = honor;
    }


    @OneToMany(mappedBy = "propertyInfo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<PropertyStaff> getPropertyStaffs() {
        return propertyStaffs;
    }


    
    public void setPropertyStaffs(Set<PropertyStaff> propertyStaffs) {
        this.propertyStaffs = propertyStaffs;
    }


    @OneToMany(mappedBy = "propertyInfo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Propertytocommunity> getPropertytocommunitys() {
        return propertytocommunitys;
    }


    
    public void setPropertytocommunitys(Set<Propertytocommunity> propertytocommunitys) {
        this.propertytocommunitys = propertytocommunitys;
    }
	
	

}