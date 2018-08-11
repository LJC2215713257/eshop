package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.GoodsinfoDAO;
import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.service.GoodsInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsinfoDAO goodsDAO;

    @Override
    public List<Goodsinfo> findAll() {
        return goodsDAO.findAll();
    }

    @Override
    public List<Goodsinfo> findByPage(int page, int count) {
        PageHelper.startPage(page,count);
        return goodsDAO.findAll();
    }

    @Override
    public Goodsinfo findByGoodsId(int gid) {
        return goodsDAO.selectByPrimaryKey(gid);
    }

    @Override
    public List<Goodsinfo> findByCateAndPage(int cateid, int page, int count) {
        PageHelper.startPage(page,count);
        return goodsDAO.findByCategory(cateid);
    }
}
