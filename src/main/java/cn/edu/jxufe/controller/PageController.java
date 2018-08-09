package cn.edu.jxufe.controller;

import cn.edu.jxufe.service.AdvertisementService;
import cn.edu.jxufe.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    private GoodsInfoService goodsService;
    @Autowired
    private AdvertisementService advService;

    @RequestMapping(value = "index")
    public String index(ModelMap map){
        map.put("goodslist",goodsService.findAll());
        map.put("advs",advService.findOnLineAdv());
        return "index";
    }

    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "find_pwd")
    public String findPwd(){
        return "find_pwd";
    }

}
