package cn.edu.jxufe.controller;

import cn.edu.jxufe.bean.Message;
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
    public Message getCartList(HttpSession session){
        Message message = new Message();
        Orderinfo cart = (Orderinfo) session.getAttribute("cart");
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null) {
            cart.setOrderGoodsList(new ArrayList<OrderinfoGoods>());
            message.setTitle("1");
            message.setEntity(cart);
            return message;
        }
        message.setTitle("0");
        return message;
    }
}
