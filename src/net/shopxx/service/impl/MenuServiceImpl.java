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
// Created on 2014年6月27日
// $Id$

package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.AdminDao;
import net.shopxx.dao.MenuDao;
import net.shopxx.entity.Menu;
import net.shopxx.service.MenuService;

import org.springframework.stereotype.Service;

/**
 * @author czllfy
 */
@Service("menuServiceImpl")
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements MenuService {

    @Resource(name = "menuDaoImpl")
    private MenuDao menuDao;

    @Resource(name = "menuDaoImpl")
    public void setBaseDao(MenuDao adminDao) {
        super.setBaseDao(adminDao);
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.MenuService#findTree()
     */
    public List<Menu> findTree() {
        return menuDao.findChildren(null, null);
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.MenuService#findRootMenuList()
     */
    @Override
    public List<Menu> findRootMenuList() {
        return menuDao.findRootMenuList();
    }
}
