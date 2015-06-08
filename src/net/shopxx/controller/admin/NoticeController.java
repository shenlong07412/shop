package net.shopxx.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Community;
import net.shopxx.entity.Notice;
import net.shopxx.entity.SystemCode;
import net.shopxx.entity.SystemCodeDetail;
import net.shopxx.service.CommunityService;
import net.shopxx.service.NoticeService;
import net.shopxx.service.SystemCodeDetailService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 公告
 * 
 * @author fangym
 * @version 1.0
 */
@Controller("noticeController")
@RequestMapping("/admin/notice")
public class NoticeController extends BaseController{

    @Resource(name = "noticeServiceImpl")
    private NoticeService noticeService;
    
    @Resource(name = "communityServiceImpl")
    private CommunityService communityService;
    
    @Resource(name = "systemCodeDetailServiceImpl")
    private SystemCodeDetailService systemCodeDetailService;
 

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        List<SystemCodeDetail> typeList = systemCodeDetailService.getListBySystemCode("gonggao");
        model.addAttribute("typeList", typeList);
        return "/admin/notice/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Notice notice,  RedirectAttributes redirectAttributes) {
 
        if (!isValid(notice)) {
            return ERROR_VIEW;
        }
 
        
        noticeService.save(notice);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model) {
        List<SystemCodeDetail> typeList = systemCodeDetailService.getListBySystemCode("gonggao");
        model.addAttribute("typeList", typeList);
        Notice notice = noticeService.find(id);
        model.addAttribute("notice", notice);
        String  communityNames = "";
        if(!StringUtils.isBlank(notice.getCommunitys())){
           String communitys[] =  notice.getCommunitys().split(",");
           for (String cid : communitys) {
               Community community = communityService.find(Long.parseLong(cid));
               if(community!=null){
                   communityNames +=(community.getName()+",");
               }
           }
           communityNames = communityNames.substring(0, communityNames.length()-1);
        }
        
        model.addAttribute("communityNames", communityNames);
        return "/admin/notice/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Notice notice , RedirectAttributes redirectAttributes) {
        if (!isValid(notice)) {
            return ERROR_VIEW;
        }
        noticeService.update(notice);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model) {
        model.addAttribute("page", noticeService.findPage(pageable));
        return "/admin/notice/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        noticeService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
