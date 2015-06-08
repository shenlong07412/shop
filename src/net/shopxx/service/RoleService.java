/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.service;

import org.springframework.stereotype.Service;

import net.shopxx.entity.Role;

/**
 * Service - 角色
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("roleServiceImpl")
public interface RoleService extends BaseService<Role, Long> {

}