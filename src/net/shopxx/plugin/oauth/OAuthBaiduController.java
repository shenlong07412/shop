package net.shopxx.plugin.oauth;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.controller.admin.BaseController;
import net.shopxx.entity.PluginConfig;
import net.shopxx.plugin.OAuthPlugin;
import net.shopxx.service.PluginConfigService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author czllfy
 */
@Controller("oAuthBaiduAdminController")
@RequestMapping("/admin/oauth_plugin/baidu_oauth")
public class OAuthBaiduController extends BaseController {

    @Resource(name = "oAuthBaiduPlugin")
    private OAuthBaiduPlugin    authPlugin;
    @Resource(name = "pluginConfigServiceImpl")
    private PluginConfigService pluginConfigService;

    /**
     * 安装
     */
    @RequestMapping(value = "/install", method = RequestMethod.POST)
    public @ResponseBody Message install() {
        if (!authPlugin.getIsInstalled()) {
            PluginConfig pluginConfig = new PluginConfig();
            pluginConfig.setPluginId(authPlugin.getId());
            pluginConfig.setIsEnabled(false);
            pluginConfigService.save(pluginConfig);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 卸载
     */
    @RequestMapping(value = "/uninstall", method = RequestMethod.POST)
    public @ResponseBody Message uninstall() {
        if (authPlugin.getIsInstalled()) {
            PluginConfig pluginConfig = authPlugin.getPluginConfig();
            pluginConfigService.delete(pluginConfig);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 设置
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String setting(ModelMap model) {
        PluginConfig pluginConfig = authPlugin.getPluginConfig();
        model.addAttribute("pluginConfig", pluginConfig);
        return "/net/shopxx/plugin/oauth/baidu_oauth_setting";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(String oAuthName, String openid, String openkey, String redirectUri, String logo,
                         String description, @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order,
                         RedirectAttributes redirectAttributes) {
        PluginConfig pluginConfig = authPlugin.getPluginConfig();
        pluginConfig.setAttribute(OAuthPlugin.OAUTH_NAME_ATTRIBUTE_NAME, oAuthName);
        pluginConfig.setAttribute("openid", openid);
        pluginConfig.setAttribute("openkey", openkey);
        pluginConfig.setAttribute("redirectUri", redirectUri);
        pluginConfig.setAttribute(OAuthPlugin.LOGO_ATTRIBUTE_NAME, logo);
        pluginConfig.setAttribute(OAuthPlugin.DESCRIPTION_ATTRIBUTE_NAME, description);
        pluginConfig.setIsEnabled(isEnabled);
        pluginConfig.setOrder(order);
        pluginConfigService.update(pluginConfig);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:/admin/oauth_plugin/list.jhtml";
    }
}
