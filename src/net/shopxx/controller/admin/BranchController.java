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

package net.shopxx.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Branch;
import net.shopxx.entity.SystemCodeDetail;
import net.shopxx.entity.Ad.Type;
import net.shopxx.entity.BaseEntity.Save;
import net.shopxx.service.BranchService;
import net.shopxx.service.SystemCodeDetailService;
import net.shopxx.util.JsonUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Ordering;

/**
 * @author czllfy
 */
@Controller("branchController")
@RequestMapping("/admin/branch")
public class BranchController extends BaseController {

    @Resource(name = "branchServiceImpl")
    private BranchService branchService;
    
    @Resource(name = "systemCodeDetailServiceImpl")
    private SystemCodeDetailService        systemCodeDetailService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("brachTree", branchService.findTree());
        List<SystemCodeDetail> branchtype = systemCodeDetailService.getListBySystemCode("branchtype");
        model.addAttribute("branchtype", branchtype);
        return "/admin/branch/addBranch";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Branch branch, Long parentId, RedirectAttributes redirectAttributes) {
        if (parentId != null) {
        	branch.setParent(branchService.find(parentId));
        }

        if (branch.getParent() != null) {
        	branch.setTreeLevel(branch.getParent().getTreeLevel() + 1);
        } else {
        	branch.setTreeLevel(0l);
        }

        if (branch.getOrder() == null && branch.getParent() != null && branch.getParent().getChildren() != null
            && branch.getParent().getChildren().size() > 0) {
        	branch.setOrder(Ordering.natural().max(branch.getParent().getChildren()).getOrder() + 1);
        } else {
        	branch.setOrder(1);
        }

        branchService.save(branch);
        Message msg = Message.success("admin.message.success");
        msg.setScript("parent.window.location.reload();");
        addFlashMessage(redirectAttributes, msg);
        return "redirect:edit.jhtml?id=" + branch.getId();
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
    	List<SystemCodeDetail> branchtype = systemCodeDetailService.getListBySystemCode("branchtype");
        model.addAttribute("branchtype", branchtype);
        model.addAttribute("branch", branchService.find(id));
        model.addAttribute("branchTree", branchService.findTree());
        return "/admin/branch/editBranch";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Branch branch, Long parentId, RedirectAttributes redirectAttributes) {
        if (parentId != null) {
        	branch.setParent(branchService.find(parentId));
        }
        if (branch.getParent() != null) {
        	branch.setTreeLevel(branch.getParent().getTreeLevel() + 1);
        } else {
        	branch.setTreeLevel(0l);
        }
        branchService.update(branch);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:edit.jhtml?id=" + branch.getId();
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Long id,Pageable pageable, ModelMap model) {
    	model.addAttribute("id", id);
        model.addAttribute("tree", JsonUtils.toJson(branchService.findRootBranchList()));
        return "/admin/branch/listBranch";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
    	branchService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
