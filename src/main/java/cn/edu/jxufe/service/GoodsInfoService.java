package cn.edu.jxufe.service;

import cn.edu.jxufe.dao.GoodsinfoDAO;
import cn.edu.jxufe.entity.Goodsinfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface GoodsInfoService {
    List<Goodsinfo> findAll();
    Goodsinfo findByGoodsId(int gid);
}
