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
// Created on 2014年7月8日
// $Id$

package net.shopxx.controller.admin;

import javax.annotation.Resource;

import net.shopxx.service.PluginService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author czllfy
 */

@Controller("adminOAuthPluginController")
@RequestMapping("/admin/oauth_plugin")
public class OAuthController extends BaseController {

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("oAuthPlugins", pluginService.getOAuthPlugins());
        return "/admin/oauth_plugin/list";
    }

}
