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

package net.shopxx.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import net.shopxx.dao.MenuDao;
import net.shopxx.entity.Menu;

import org.springframework.stereotype.Repository;

/**
 * @author czllfy
 */
@Repository("menuDaoImpl")
public class MenuDaoImpl extends BaseDaoImpl<Menu, Long> implements MenuDao {

    public List<Menu> findChildren(Menu productCategory, Integer count) {
        TypedQuery<Menu> query;
        String jpql = "select m from Menu m  order by m.parent,m.order,m.name asc";
        query = entityManager.createQuery(jpql, Menu.class).setFlushMode(FlushModeType.COMMIT);
        if (count != null) {
            query.setMaxResults(count);
        }
        return sort(query.getResultList(), productCategory);
    }

    /*
     * 查找顶级菜单列表
     * @see net.shopxx.dao.MenuDao#findRootMenuList()
     */
    public List<Menu> findRootMenuList() {
        TypedQuery<Menu> query;
        String jpql = "select m from Menu m where m.parent is null order by m.parent,m.order,m.name asc";
        query = entityManager.createQuery(jpql, Menu.class).setFlushMode(FlushModeType.COMMIT);
        return query.getResultList();
    }

    /**
     * 排序商品分类
     * 
     * @param productCategories 商品分类
     * @param parent 上级商品分类
     * @return 商品分类
     */
    private List<Menu> sort(List<Menu> productCategories, Menu parent) {
        List<Menu> result = new ArrayList<Menu>();
        if (productCategories != null) {
            for (Menu productCategory : productCategories) {
                if ((productCategory.getParent() != null && productCategory.getParent().equals(parent))
                    || (productCategory.getParent() == null && parent == null)) {
                    result.add(productCategory);
                    result.addAll(sort(productCategories, productCategory));
                }
            }
        }
        return result;
    }
}
