package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.OrderinfoDAO;
import cn.edu.jxufe.dao.OrderinfoGoodsDAO;
import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.entity.OrderinfoGoods;
import cn.edu.jxufe.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderinfoDAO dao;
    @Autowired
    private OrderinfoGoodsDAO odao;


    @Override
    public List<Orderinfo> findByBuyerId(int buy_id) {
        return dao.findByBuyerId(buy_id);
    }

    @Override
    public int delectOrder(int orderId) {
        Orderinfo order = new Orderinfo();
        order.setOrderId(orderId);
        return dao.delete(order);
    }

    @Override
    public int payMoney(int orderId) {
        Orderinfo order = new Orderinfo();
        order.setOrderId(orderId);
        order.setOrderState(20);
        return dao.update(order);
    }

    @Override
    public int insertSelective(Orderinfo orderinfo) {
        try {
            for (OrderinfoGoods og : orderinfo.getOrderGoodsList()) {
                odao.insertSelective(og);
            }
            dao.insertSelective(orderinfo);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String findByOrderSn(String sn) {
        return dao.findByOrderSn(sn);
    }
}
