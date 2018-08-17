package cn.edu.jxufe.service;

import cn.edu.jxufe.dao.GoodsinfoDAO;
import cn.edu.jxufe.entity.Goodsinfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface GoodsInfoService {
    List<Goodsinfo> findAll();
    List<Goodsinfo> findByPage(int page,int count);
    Goodsinfo findByGoodsId(int gid);
    List<Goodsinfo> findByGoodsIdSet(List<Integer> gids);
    List<Goodsinfo> findByCateAndPage(int cateid,int page,int count,String sort,String orderBy);
    List<Goodsinfo> findGoodsByGname(String str);
    public int uploadGoods(String goodsName,String goodsSubtitle,String goodsImage,Integer memberId,Date createTime);
}
