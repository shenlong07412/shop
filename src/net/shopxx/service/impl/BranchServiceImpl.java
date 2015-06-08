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

package net.shopxx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.BranchDao;
import net.shopxx.entity.Branch;
import net.shopxx.service.BranchService;

import org.springframework.stereotype.Service;

/**
 * @author czllfy
 */
@Service("branchServiceImpl")
public class BranchServiceImpl extends BaseServiceImpl<Branch, Long> implements BranchService {

    @Resource(name = "branchDaoImpl")
    private BranchDao branchDao;

    @Resource(name = "branchDaoImpl")
    public void setBaseDao(BranchDao branchDao) {
        super.setBaseDao(branchDao);
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.BranchService#findTree()
     */
    public List<Branch> findTree() {
        return branchDao.findChildren(null, null);
    }

    /*
     * (non-Javadoc)
     * @see net.shopxx.service.BranchService#findRootBranchList()
     */
    @Override
    public List<Branch> findRootBranchList() {
        return branchDao.findRootBranchList();
    }

}
