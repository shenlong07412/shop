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
// Created on 2014年7月4日
// $Id$

package net.shopxx.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import net.shopxx.dao.BranchDao;
import net.shopxx.entity.Branch;

import org.springframework.stereotype.Repository;

/**
 * Dao - 组枳机构
 * 
 * @author czllfy
 */
@Repository("branchDaoImpl")
public class BranchDaoImpl extends BaseDaoImpl<Branch, Long> implements BranchDao {

	 public List<Branch> findChildren(Branch branchCategory, Integer count) {
	        TypedQuery<Branch> query;
	        String jpql = "select m from Branch m  order by m.parent,m.order,m.name asc";
	        query = entityManager.createQuery(jpql, Branch.class).setFlushMode(FlushModeType.COMMIT);
	        if (count != null) {
	            query.setMaxResults(count);
	        }
	        return sort(query.getResultList(), branchCategory);
	    }

	    /*
	     * 查找顶级部门列表
	     * @see net.shopxx.dao.Branch#findRootBranchList()
	     */
	    public List<Branch> findRootBranchList() {
	        TypedQuery<Branch> query;
	        String jpql = "select m from Branch m where m.parent is null order by m.parent,m.order,m.name asc";
	        query = entityManager.createQuery(jpql, Branch.class).setFlushMode(FlushModeType.COMMIT);
	        return query.getResultList();
	    }

	    /**
	     * 排序部门
	     * 
	     * @param productCategories 部门
	     * @param parent 上级部门
	     * @return 部门
	     */
	    private List<Branch> sort(List<Branch> branchCategories, Branch parent) {
	        List<Branch> result = new ArrayList<Branch>();
	        if (branchCategories != null) {
	            for (Branch branchCategory : branchCategories) {
	                if ((branchCategory.getParent() != null && branchCategory.getParent().equals(parent))
	                    || (branchCategory.getParent() == null && parent == null)) {
	                    result.add(branchCategory);
	                    result.addAll(sort(branchCategories, branchCategory));
	                }
	            }
	        }
	        return result;
	    }

}
