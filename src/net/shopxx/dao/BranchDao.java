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

package net.shopxx.dao;

import java.util.List;

import net.shopxx.entity.Branch;

/**
 * 组织机构 - 地区
 * 
 * @author czllfy
 */
public interface BranchDao extends BaseDao<Branch, Long> {

    public List<Branch> findChildren(Branch branchCategory, Integer count);

    public List<Branch> findRootBranchList();
}
