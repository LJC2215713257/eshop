package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.service.AdvertisementService;
import cn.edu.jxufe.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @Autowired
    private GoodsInfoService goodsService;
    @Autowired
    private AdvertisementService advService;

    @RequestMapping(value = "index")
    public String index(ModelMap map,HttpSession session){
        map.put("goodslist",goodsService.findByPage(1,10));
        map.put("advs",advService.findOnLineAdv());
        if(session.getAttribute("orderNum")==null){
            session.setAttribute("orderNum",0);
        }
        map.put("orderNum",session.getAttribute("orderNum"));
        return "index";
    }

    @RequestMapping(value = "user")
    public String userPage(HttpSession session){
        if(session.getAttribute("user")==null){
            return "login";
        }else {
            return "forward:user/info";
        }
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

    @RequestMapping(value = "change_name")
    public String changeName(){
        return "change_name";
    }

    @RequestMapping(value = "change_tel")
    public String changeTel(){
        return "change_tel";
    }

    @RequestMapping(value = "cart")
    public String opneCart(HttpSession session,ModelMap map){
        Orderinfo cart = (Orderinfo) session.getAttribute("cart");
        if(cart!=null){
            map.put("cart",cart);
            if(session.getAttribute("orderNum")==null){
                session.setAttribute("orderNum",0);
            }
            map.put("orderNum",session.getAttribute("orderNum"));
        }
        return "cart";
    }
}
