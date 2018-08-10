package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/goods")
@Controller
public class GoodsController {
    @Autowired
    private GoodsInfoService goodsInfoService;

    @RequestMapping(value = "info{gid}")
    public String getGoodsInfo(@PathVariable("gid") int gid, ModelMap map){
        System.out.println("---------------------------------"+gid);
        Goodsinfo goods = goodsInfoService.findByGoodsId(gid);
        if(goods==null&&goods.getGoodsState()==0){
            return "404";
        }
        map.put("goods",goods);
        return "product";
    }

    @RequestMapping(value = "next_page")
    @ResponseBody
    public Object getNextPage(@RequestParam(name = "page") int page,@RequestParam(name = "count") int count){
        return goodsInfoService.findByPage(page,count);
    }
}
