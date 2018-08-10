package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.entity.OrderinfoGoods;

import java.util.List;

public interface OrderInfoGoodsService {
    OrderinfoGoods findByRecId(int recId);
    int delectOrderGoods(int recId);
    int insertSelective(OrderinfoGoods og);
}
