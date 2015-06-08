package net.shopxx.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Sensitivity;
import net.shopxx.service.SensitivityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.ffcs.util.KeywordFilter;

/**
 * Controller - 敏感词
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Controller("sensitivityController")
@RequestMapping("/admin/sensitivity")
public class SensitivityController extends BaseController{
	
	@Resource(name = "sensitivityServiceImpl")
	private SensitivityService sensitivityService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list( Pageable pageable, ModelMap model) {
		if(pageable.getSearchValue()!=null && "".equals(pageable.getSearchValue().trim())){
			pageable.setSearchValue(null);
		}
		Page<Sensitivity> page =sensitivityService.findPage(pageable);
		model.addAttribute("page", page);
		return "/admin/sensitivity/list";
	}

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        return "/admin/sensitivity/add";
    }	
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save")
    public String save(Sensitivity sensitivity,Long areaId,RedirectAttributes redirectAttributes,ModelMap model) {
    	sensitivity.setDataflg(1l);
    	sensitivityService.save(sensitivity);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
    }    

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		Sensitivity sensitivity =sensitivityService.find(id);
		model.addAttribute("sensitivity", sensitivity);
		return "/admin/sensitivity/edit";
	}	    

    /**
     * 更新
     */
    @RequestMapping(value = "/update")
    public String update(Sensitivity sensitivity,RedirectAttributes redirectAttributes,ModelMap model) {
    	sensitivityService.update(sensitivity);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
    } 	
	
	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("sensitivity", sensitivityService.find(id));
		return "/admin/sensitivity/view";
	}		

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids.length >= sensitivityService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		sensitivityService.delete(ids);
		return SUCCESS_MESSAGE;
	}		
	
	
	/**
	 * 获取信息
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<Long, String> getInfo(Long id) {
		List<Sensitivity> list = sensitivityService.findAll();
		List<String> strList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (Sensitivity si : list) {
				strList.add(si.getSearch());
			}
		}
		KeywordFilter filter= new KeywordFilter();
		filter.addKeywords(strList);
		String txt="我不是法轮功爱好者，我志向是当主席!";
		List<String> set = filter.getTxtKeyWords(txt);
		String result2 = filter.str_replace1(set.toString(), "*", txt);
		Sensitivity sensitivity = sensitivityService.find(id);
		Map<Long, String> map = new HashMap<Long, String>();
		map.put(sensitivity.getId(), sensitivity.getSearch());
		map.put(1l, result2);
		return map;
	}

}
