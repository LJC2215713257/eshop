package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.OrderinfoGoodsDAO;
import cn.edu.jxufe.entity.OrderinfoGoods;
import cn.edu.jxufe.service.OrderInfoGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoGoodsServiceImpl implements OrderInfoGoodsService {
    @Autowired
    private OrderinfoGoodsDAO ogDAO;


    @Override
    public OrderinfoGoods findByRecId(int recId) {
        OrderinfoGoods orderinfoGoods = new OrderinfoGoods();
        orderinfoGoods.setRecId(recId);
        return ogDAO.selectByPrimaryKey(recId);
    }

    @Override
    public int delectOrderGoods(int recId) {
        OrderinfoGoods orderinfoGoods = new OrderinfoGoods();
        orderinfoGoods.setRecId(recId);
        return ogDAO.delete(orderinfoGoods);
    }

    @Override
    public int insertSelective(OrderinfoGoods og) {
        return ogDAO.insertSelective(og);
    }
}
