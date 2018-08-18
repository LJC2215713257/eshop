package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.GoodsinfoDAO;
import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.service.GoodsInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Goodsinfo> findByGoodsIdSet(List<Integer> gids) {
        return goodsDAO.findByGoodsIdSet(gids);
    }

    @Override
    public List<Goodsinfo> findByCateAndPage(int cateid, int page, int count, String sort,String orderBy) {
        PageHelper.startPage(page,count);
        Map map = new HashMap();
        map.put("cateid",cateid);
        map.put("sort",sort);
        map.put("orderBy",orderBy);
        return goodsDAO.findByCategory(map);
    }

    @Override
    public List<Goodsinfo> findGoodsByGname(String str) {
        Map map=new HashMap();
        map.put("goodsName",str);
        return goodsDAO.findAllGoodsByProperty(map);
    }

    @Override
    public int uploadGoods(String goodsName, String goodsSubtitle, String goodsImage, Integer memberId, Date createTime) {
        Goodsinfo g=new Goodsinfo();
        g.setGoodsName(goodsName);
        g.setGoodsSubtitle(goodsSubtitle);
        g.setGoodsImage(goodsImage);
        g.setMemberId(memberId);
        g.setCreateTime(createTime);
        return goodsDAO.insertSelective(g);
    }

    @Override
    public int commentPlus(int gid) {
        return goodsDAO.commentPlus(gid);
    }

    @Override
    public List<Goodsinfo> findByAuthor(int mid) {
        return goodsDAO.findByAuthor(mid);
    }

    @Override
    public List<Goodsinfo> findCollect(Integer mid) {
        if (mid != null) {
            return goodsDAO.findCollect(mid);
        }
        return null;
    }


}
