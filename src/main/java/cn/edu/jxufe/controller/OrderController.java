package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.entity.OrderinfoGoods;
import cn.edu.jxufe.service.GoodsInfoService;
import cn.edu.jxufe.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping(value = "order")
@Controller
public class OrderController {
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private GoodsInfoService goodsInfoService;

    @RequestMapping(value = "list")
    public String listOrderInfo(ModelMap map,HttpSession session){
        Memberinfo menber = (Memberinfo) session.getAttribute("user");
        if(menber!=null&&menber.getMemberId()!=0){
            map.put("orders",orderInfoService.findByBuyerId(1));
        }
        return "order_list";
    }

    @RequestMapping(value="addGoods")
    @ResponseBody
    public String addToCart(HttpSession session,int goodsId){
        Memberinfo menber = (Memberinfo) session.getAttribute("user");
        if(menber!=null&&menber.getMemberId()!=null) {
            Orderinfo order = (Orderinfo) session.getAttribute("cart");
            if (order == null) {
                order = new Orderinfo();
                order.setOrderState(10);
                order.setBuyerId(menber.getMemberId());
                order.setBuyerName(menber.getMemberName());
                order.setBuyerTel(menber.getMemberMobile());
                order.setOrderGoodsList(new ArrayList<OrderinfoGoods>());
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
                if (orderGoods==null) {
                    orderGoods.setGoodsId(goodsId);
                    orderGoods.setGoodsPrice(goodsinfo.getGoodsPrice());
                    orderGoods.setGoodsPayPrice(goodsinfo.getGoodsSellPrice());
                    orderGoods.setGoodsName(goodsinfo.getGoodsName());
                    orderGoods.setCreatedTime(new Date());
                    orderGoods.setGoodsNum(1);
                }else{
                    orderGoods.setGoodsNum(orderGoods.getGoodsNum()+1);
                }

                List<OrderinfoGoods> ol = order.getOrderGoodsList();
                if(ol==null){
                    ol = new ArrayList<OrderinfoGoods>();
                }
                ol.add(orderGoods);
                order.setOrderGoodsList(ol);
                order.setOrderAmount(order.getOrderAmount()+orderGoods.getGoodsPayPrice());
                session.setAttribute("cart",order);
                return "1";
            }
            return "4";
        }else{
            return "4";
        }
    }

    @RequestMapping(value = "savaCart")
    public String saveCart(HttpSession session,ModelMap map){
        Memberinfo menber = (Memberinfo) session.getAttribute("user");
        
    }

}
