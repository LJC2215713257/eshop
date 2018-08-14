package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Orderinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OrderinfoDAO继承基类
 */
@Repository
public interface OrderinfoDAO extends MyBatisBaseDao<Orderinfo, Orderinfo> {
    List<Orderinfo> findByBuyerId(int buy_id);
    String findByOrderSn(String sn);
}