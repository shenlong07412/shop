/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.shiro.SecurityUtils;

import net.shopxx.entity.BaseEntity;

/**
 * Listener - 创建日期、修改日期处理
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
public class EntityListener {

    /**
     * 保存前处理
     * 
     * @param entity 基类
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreateDate(new Date());
        entity.setCreateby(SecurityUtils.getSubject().getPrincipal() != null ? SecurityUtils.getSubject().getPrincipal().toString() : "");
        entity.setModifyDate(new Date());
        entity.setUpdateby(SecurityUtils.getSubject().getPrincipal() != null ? SecurityUtils.getSubject().getPrincipal().toString() : "");
    }

    /**
     * 更新前处理
     * 
     * @param entity 基类
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setModifyDate(new Date());
        entity.setUpdateby(SecurityUtils.getSubject().getPrincipal() != null ? SecurityUtils.getSubject().getPrincipal().toString() : "");
    }
}
