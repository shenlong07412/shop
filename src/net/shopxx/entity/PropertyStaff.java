/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 物业公司相关工作人员基本信息
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_property_staff")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_property_staff_sequence")
public class PropertyStaff extends OrderEntity {

    private static final long serialVersionUID = 6378690137917969573L;

	private String staffNo;
	
	private String name;
	
	private Long sex;
	
	private String photo;
	
	private String identity;
	
	private String education;
	
	private String phone;
	
	private String password;
	
	private Long smsInvitation;
	
	private Long authority;
	
	private String officePhone;
	
	private String department;
	
	private String job;
	
	private Date entryTime;
	
	private Date turnoverTime;
	
	private String workCharacter;
	
	private PropertyInfo propertyInfo; 

    private Set<Communitytostaff> communitytostaffs = new HashSet<Communitytostaff>();
	
    @OneToMany(mappedBy = "propertyStaff", fetch = FetchType.LAZY ,cascade = CascadeType.REMOVE)
    public Set<Communitytostaff> getCommunitytostaffs() {
        return communitytostaffs;
    }


    
    public void setCommunitytostaffs(Set<Communitytostaff> communitytostaffs) {
        this.communitytostaffs = communitytostaffs;
    }


    public String getStaffNo() {
        return staffNo;
    }

    
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public Long getSex() {
        return sex;
    }

    
    public void setSex(Long sex) {
        this.sex = sex;
    }

    
    public String getPhoto() {
        return photo;
    }

    
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    public String getIdentity() {
        return identity;
    }

    
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    
    public String getEducation() {
        return education;
    }

    
    public void setEducation(String education) {
        this.education = education;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    
    public Long getSmsInvitation() {
        return smsInvitation;
    }

    
    public void setSmsInvitation(Long smsInvitation) {
        this.smsInvitation = smsInvitation;
    }

    
    public Long getAuthority() {
        return authority;
    }

    
    public void setAuthority(Long authority) {
        this.authority = authority;
    }

    
    public String getOfficePhone() {
        return officePhone;
    }

    
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    
    public String getDepartment() {
        return department;
    }

    
    public void setDepartment(String department) {
        this.department = department;
    }

    
    public String getJob() {
        return job;
    }

    
    public void setJob(String job) {
        this.job = job;
    }

    
    public Date getEntryTime() {
        return entryTime;
    }

    
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    
    public Date getTurnoverTime() {
        return turnoverTime;
    }

    
    public void setTurnoverTime(Date turnoverTime) {
        this.turnoverTime = turnoverTime;
    }

    
 


    
    public String getWorkCharacter() {
        return workCharacter;
    }



    
    public void setWorkCharacter(String workCharacter) {
        this.workCharacter = workCharacter;
    }



    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public PropertyInfo getPropertyInfo() {
        return propertyInfo;
    }

    
    public void setPropertyInfo(PropertyInfo propertyInfo) {
        this.propertyInfo = propertyInfo;
    }
	
	
	

}