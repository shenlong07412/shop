package net.shopxx.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.shopxx.entity.ProductCategory;
import net.shopxx.entity.ZtreeBean;
import net.shopxx.service.ProductCategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * Controller - 商品类别授权管理
 * 
 * @author fangym
 * @version 1.0
 */
@Controller("ProductMerchantCategoryController")
@RequestMapping("/admin/productMerchantCategory")
public class ProductMerchantCategoryController extends BaseController {

 
    @Resource(name = "productCategoryServiceImpl")
    private ProductCategoryService productCategoryService;
    
 
 
 
    /**
     * 获取类别树
     * @param response
     * @param model
     */
    @RequestMapping(value = "/getCategroy")
    public @ResponseBody  void getCategroy(HttpServletResponse response,Long treeId, ModelMap model) {
        
        response.setCharacterEncoding("UTF-8");  
        PrintWriter out;
        try {
            out = response.getWriter();
            
            List<ProductCategory> list = productCategoryService.findTree();
            List<ZtreeBean> tree = new ArrayList<ZtreeBean>();
            for(ProductCategory p :list){
                setZtreeBean(tree,p.getId(),p.getParent()==null?null:p.getParent().getId(),p.getName(),p.getParent()==null?true:false,false);
            }
            
            String str = JSON.toJSONString(tree);  
            out.print(str);  
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public List<ZtreeBean> setZtreeBean(List<ZtreeBean> list,Long treeId,Long parentId,String name,Boolean isParent,Boolean isClick) {
        ZtreeBean zb=new ZtreeBean();
        if(isClick==null){
            isClick=true;
        }
        zb.setTreeId(treeId.intValue());
        if(parentId!=null){
            zb.setParentId(parentId.intValue());
        }
        zb.setName(name);
        zb.setIsParent(isParent);
        zb.setIsClick(isClick);
        list.add(zb);
        return list;
    }
    
}
