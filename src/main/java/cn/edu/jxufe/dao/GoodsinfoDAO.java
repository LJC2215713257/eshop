package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.GoodsCategory;
import cn.edu.jxufe.entity.Goodsinfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * GoodsinfoDAO继承基类
 */
@Repository
public interface GoodsinfoDAO extends MyBatisBaseDao<Goodsinfo, Integer> {
    List<Goodsinfo> findByCategory(Map map);
    List<Goodsinfo> findByGoodsIdSet(@Param("gids") List<Integer> gids);
    public List<Goodsinfo>    findAllGoodsByProperty(@Param("goods")Map map);
    int commentPlus(int gid);
    List<Goodsinfo> findByAuthor(int mid);
    List<Goodsinfo> findCollect(int mid);
}