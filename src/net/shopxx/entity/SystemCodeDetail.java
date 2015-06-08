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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 系统字典表
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_system_code_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_system_code_detail_sequence")
public class SystemCodeDetail extends OrderEntity {

    private static final long serialVersionUID = 4734095606628270853L;

    private SystemCode        systemCode;                           //编码

    private String            codeDetail;                            // 详细编码

    private String            name;                                   // 名称
    
//	/** 投诉保修信息 */
//	private Set<ComplaintRep> complaintReps = new HashSet<ComplaintRep>();
 

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public SystemCode getSystemCode() {
        return systemCode;
    }

    
    public void setSystemCode(SystemCode systemCode) {
        this.systemCode = systemCode;
    }

   

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }


    
    public String getCodeDetail() {
        return codeDetail;
    }


    
    public void setCodeDetail(String codeDetail) {
        this.codeDetail = codeDetail;
    }

//	@OneToMany(mappedBy = "systemCodeDetail", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//	public Set<ComplaintRep> getComplaintReps() {
//		return complaintReps;
//	}


//	public void setComplaintReps(Set<ComplaintRep> complaintReps) {
//		this.complaintReps = complaintReps;
//	}


  

    
    
}
