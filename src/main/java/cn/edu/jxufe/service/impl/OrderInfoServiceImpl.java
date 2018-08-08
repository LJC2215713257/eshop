package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.OrderinfoDAO;
import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderinfoDAO dao;


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
}
