package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Orderinfo;

import java.util.List;

public interface OrderInfoService {
    List<Orderinfo> findByBuyerId(int buy_id);
    int delectOrder(int orderId);
    int payMoney(int orderId);
    int insertSelective(Orderinfo orderinfo);
    String findByOrderSn(String sn);
}
