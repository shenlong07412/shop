/*
\ * Copyright 2014 Alibaba Group Holding Ltd.
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

package net.shopxx.controller.admin;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Ad.Type;
import net.shopxx.entity.Menu;
import net.shopxx.service.MenuService;
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
@Controller("menuController")
@RequestMapping("/admin/menu")
public class MenuControl extends BaseController {

    @Resource(name = "menuServiceImpl")
    private MenuService menuService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        model.addAttribute("menuTree", menuService.findTree());
        return "/admin/menu/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Menu menu, Long parentId, RedirectAttributes redirectAttributes) {
        if (!isValid(menu)) {
            return ERROR_VIEW;
        }
        if (parentId != null) {
            menu.setParent(menuService.find(parentId));
        }

        if (menu.getParent() != null) {
            menu.setLevel(menu.getParent().getLevel() + 1);
        } else {
            menu.setLevel(0);
        }

        if(menu.getOrder()==null){
        	if(menu.getParent() != null && menu.getParent().getChildren() != null
                    && menu.getParent().getChildren().size() > 0){
        		menu.setOrder(Ordering.natural().max(menu.getParent().getChildren()).getOrder() + 1);
        	}else{
        		menu.setOrder(1);
        	}
        }

        menuService.save(menu);
        Message msg = Message.success("admin.message.success");
        msg.setScript("parent.window.location.reload();");
        addFlashMessage(redirectAttributes, msg);
        return "redirect:edit.jhtml?id=" + menu.getId();
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        model.addAttribute("types", Type.values());
        model.addAttribute("ad", menuService.find(id));
        model.addAttribute("menuTree", menuService.findTree());
        return "/admin/menu/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Menu menu, Long parentId, RedirectAttributes redirectAttributes) {
        if (!isValid(menu)) {
            return ERROR_VIEW;
        }
        if (parentId != null) {
            menu.setParent(menuService.find(parentId));
        }
        if (menu.getParent() != null) {
            menu.setLevel(menu.getParent().getLevel() + 1);
        } else {
            menu.setLevel(0);
        }
        menuService.update(menu);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:edit.jhtml?id=" + menu.getId();
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("tree", JsonUtils.toJson(menuService.findRootMenuList()));
        return "/admin/menu/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        menuService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
