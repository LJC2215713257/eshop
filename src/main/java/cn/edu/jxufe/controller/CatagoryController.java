package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.GoodsCategory;
import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.service.CategoryService;
import cn.edu.jxufe.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.List;

@RequestMapping(value = "cate")
@Controller
public class CatagoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsInfoService goodsInfoService;


    @RequestMapping(value = "category")
    public String catePage(ModelMap map){
        map.put("cates",categoryService.findAll());
        map.put("goods",goodsInfoService.findByPage(1,10));
        return "category";
    }

    @RequestMapping(value = "findcate{catId}")
    @ResponseBody
    public List<Goodsinfo> findCate(@PathVariable int catId,@RequestParam(defaultValue = "1") int page){
        return goodsInfoService.findByCateAndPage(catId,page,10);
    }

}
