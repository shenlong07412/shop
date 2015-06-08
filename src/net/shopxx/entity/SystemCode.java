/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 系统字典表
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_system_code")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_system_code_sequence")
public class SystemCode extends OrderEntity {

    private static final long serialVersionUID = 4734095606628270853L;

    private String            code;                                    // 编码

    private String            name;                                    // 名称

    
    private Set<SystemCodeDetail> systemCodeDetails = new HashSet<SystemCodeDetail>();//详细编码
    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }


    @OneToMany(mappedBy = "systemCode", fetch = FetchType.LAZY)
    public Set<SystemCodeDetail> getSystemCodeDetails() {
        return systemCodeDetails;
    }


    
    public void setSystemCodeDetails(Set<SystemCodeDetail> systemCodeDetails) {
        this.systemCodeDetails = systemCodeDetails;
    }


    
 

    
    
}
