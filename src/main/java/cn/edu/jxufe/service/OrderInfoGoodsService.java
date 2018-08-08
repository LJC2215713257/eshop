package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Orderinfo;

import java.util.List;

public interface OrderInfoGoodsService {
    int findByRecId(int recId);
    int delectOrderGoods(int recId);
}
