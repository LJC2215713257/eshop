package cn.edu.jxufe.controller;

import cn.edu.jxufe.bean.Cart;
import cn.edu.jxufe.bean.Message;
import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.entity.OrderinfoGoods;
import cn.edu.jxufe.service.GoodsInfoService;
import cn.edu.jxufe.service.OrderInfoGoodsService;
import cn.edu.jxufe.service.OrderInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RequestMapping(value = "order")
@Controller
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private OrderInfoGoodsService orderGoodsService;

    @RequestMapping(value = "list")
    public String listOrderInfo(ModelMap map,HttpSession session){
        Memberinfo menber = (Memberinfo) session.getAttribute("user");
        if(menber!=null&&menber.getMemberId()!=0){
            map.put("orders",orderInfoService.findByBuyerId(menber.getMemberId()));
        }
        return "order_list";
    }

    @RequestMapping(value="addGoods")
    @ResponseBody
    public Message addToCart(HttpSession session,int goodsId){
        Message message = new Message();
        Memberinfo menber = (Memberinfo) session.getAttribute("user");
        System.out.println(menber);


        if(menber!=null&&menber.getMemberId()!=null) {
            Orderinfo order = (Orderinfo) session.getAttribute("cart");
            if (order == null) {
                order = new Orderinfo();
                order.setOrderState(10);
                order.setBuyerId(menber.getMemberId());
                order.setBuyerName(menber.getMemberName());
                order.setBuyerTel(menber.getMemberMobile());
                order.setOrderGoodsList(new ArrayList<OrderinfoGoods>());
                order.setOrderAmount(0l);
                order.setOrderSn(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+(new Random(100).nextInt(2)));
            }
            OrderinfoGoods orderGoods = null;
            for(OrderinfoGoods gd:order.getOrderGoodsList()){
                if(gd.getGoodsId()==goodsId){
                    orderGoods=gd;
                    break;
                }
            }

            Goodsinfo goodsinfo = goodsInfoService.findByGoodsId(goodsId);
            if(goodsinfo!=null) {
                List<OrderinfoGoods> ol = order.getOrderGoodsList();
                if(ol==null){
                    ol = new ArrayList<OrderinfoGoods>();
                }
                if (orderGoods==null) {
                    orderGoods = new OrderinfoGoods();
                    orderGoods.setGoodsId(goodsId);
                    orderGoods.setGoodsPrice(new BigDecimal(goodsinfo.getGoodsPrice()));
                    orderGoods.setGoodsPayPrice(goodsinfo.getGoodsSellPrice().longValue());
                    orderGoods.setGoodsName(goodsinfo.getGoodsName());
                    orderGoods.setCreatedTime(new Date());
                    orderGoods.setImageUrl(goodsinfo.getGoodsImage());
                    orderGoods.setGoodsNum(1);
                    ol.add(orderGoods);
                }else{
                    orderGoods.setGoodsNum(orderGoods.getGoodsNum()+1);
                }
                order.setOrderGoodsList(ol);
                order.setOrderAmount(order.getOrderAmount()+orderGoods.getGoodsPayPrice());
                session.setAttribute("cart",order);
                message.setTitle("1");
                if(session.getAttribute("orderNum")==null){
                    session.setAttribute("orderNum",new Integer(1));
                }else{
                    Integer num = (Integer) session.getAttribute("orderNum");
                    num = num+1;
                    session.removeAttribute("orderNum");
                    session.setAttribute("orderNum",num);
                }

                return message;
            }
            message.setTitle("4");
            return message;
        }else{
            message.setTitle("5");
            return message;
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Message update(@RequestParam(name = "cart") String cart, HttpSession session){
        List<Cart> obj= JSON.parseArray(cart,Cart.class);
        System.out.println("update "+obj.size());
        System.out.println();
        Message message = new Message();
        Orderinfo order = (Orderinfo) session.getAttribute("cart");
        if(order!=null) {
            for (Cart c:obj){
                for(OrderinfoGoods og:order.getOrderGoodsList()){
                    if(og.getGoodsId()==c.getGoodsId()){
                        order.setOrderAmount(order.getOrderAmount()+(c.getNum()-og.getGoodsNum())*og.getGoodsPayPrice());
                        if(c.getNum()==0){
                            order.getOrderGoodsList().remove(og);
                        }else {
                            og.setGoodsNum(c.getNum());
                        }
                        break;
                    }
                }
            }
        }
        message.setTitle("1");
        return message;
    }

    @RequestMapping(value = "savaCart")
    public String saveCart(HttpSession session){
        Memberinfo menber = (Memberinfo) session.getAttribute("user");
        if(menber!=null){
            Orderinfo orderinfo = (Orderinfo) session.getAttribute("cart");
            if(orderinfo!=null){
                orderinfo.setCreatedTime(new Date());
                orderInfoService.insertSelective(orderinfo);
                System.out.println();
                System.out.println();
                String order_id = ""+orderInfoService.findByOrderSn(orderinfo.getOrderSn());
                for(OrderinfoGoods og:orderinfo.getOrderGoodsList()){
                    og.setOrderId(order_id);
                    og.setCreatedTime(new Date());
                    orderGoodsService.insertSelective(og);
                }
                session.removeAttribute("cart");
                if(session.getAttribute("orderNum")!=null){
                    session.removeAttribute("orderNum");
                }
                session.setAttribute("orderNum",new Integer(0));
            }
        }
        return "forward:/order/list";
    }

    @RequestMapping(value = "payfororder")
    @ResponseBody
    public Message payForOrder(@RequestParam(name = "orderid",defaultValue = "null") Integer orderid,HttpSession session){
        Message msg = new Message();
        if(orderid!=null){
            Orderinfo order = orderInfoService.findByOid(orderid);
            if(order!=null){
                Orderinfo temp = new Orderinfo();
                temp.setOrderId(orderid);
                temp.setOrderState(20);
                temp.setPaymentTime(new Date());
                orderInfoService.updateOrder(temp);
                msg.setTitle("1");
                session.removeAttribute("order_state");
                order.setOrderState(20);
                order.setPaymentTime(new Date());
                session.setAttribute("order_state",order);
            }else{
                msg.setTitle("0");
            }
        }else{
            msg.setTitle("-1");
        }
        return msg;
    }

    @RequestMapping(value = "deletefororder")
    @ResponseBody
    public Message deleteForOrder(@RequestParam(name = "orderid",defaultValue = "null") Integer orderid){
        Message msg = new Message();
        if(orderid!=null){
            Orderinfo order = orderInfoService.findByOid(orderid);
            if(order!=null){
                Orderinfo temp = new Orderinfo();
                temp.setOrderId(orderid);
                temp.setOrderState(0);
                temp.setUpdatedTime(new Date());
                orderInfoService.updateOrder(temp);
                msg.setTitle("1");
            }else{
                msg.setTitle("0");
            }
        }else{
            msg.setTitle("-1");
        }
        return msg;
    }

    @RequestMapping(value = "statepage")
    public String pageState(HttpSession session,ModelMap map){
        Orderinfo order = (Orderinfo) session.getAttribute("order_state");
        System.out.println(order);
        if(order!=null){
            map.put("os",order);
            return "return_state";
        }
        return "404";
    }
}
