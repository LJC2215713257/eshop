package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.entity.OrderinfoGoods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RequestMapping("/cart")
@Controller
public class CartController {
    @RequestMapping(value = "list")
    @ResponseBody
    public Object getCartList(HttpSession session){
        Orderinfo cart = (Orderinfo) session.getAttribute("cart");
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null) {
            cart.setOrderGoodsList(new ArrayList<OrderinfoGoods>());
            return cart;
        }
        return "{\"msg\":\"error\"}";
    }
}
