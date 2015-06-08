package net.shopxx.controller.admin;

import javax.annotation.Resource;

import net.shopxx.LogConfig;
import net.shopxx.Message;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.service.LogConfigService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 日志配置管理
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("adminLogConfigController")
@RequestMapping("/admin/logConfig")
public class LogConfigController extends BaseController{
	
	@Resource(name = "logConfigServiceImpl")
	private LogConfigService logConfigService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( Pageable pageable, ModelMap model) {
		if(pageable.getSearchValue()!=null && "".equals(pageable.getSearchValue().trim())){
			pageable.setSearchValue(null);
		}
		Page<LogConfig> page =logConfigService.findPage(pageable);
		model.addAttribute("page", page);
		return "/admin/logConfig/list";
	}

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "/admin/logConfig/add";
    }	
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save")
    public String save(LogConfig logConfig,RedirectAttributes redirectAttributes,ModelMap model) {
    	logConfigService.save(logConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
    }    

	/**
	 * 日志管理信息编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		LogConfig logConfig =logConfigService.find(id);
		model.addAttribute("logConfig", logConfig);
		return "/admin/logConfig/edit";
	}	    

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public String update(LogConfig logConfig,RedirectAttributes redirectAttributes,ModelMap model) {
    	logConfigService.update(logConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
    } 	
	
	/**
	 * 日志管理信息查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("logConfig", logConfigService.find(id));
		return "/admin/logConfig/view";
	}		

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= logConfigService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		logConfigService.delete(ids);
		return SUCCESS_MESSAGE;
	}	
	
}
