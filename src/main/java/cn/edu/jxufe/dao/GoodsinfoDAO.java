package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Goodsinfo;
import org.springframework.stereotype.Repository;

/**
 * GoodsinfoDAO继承基类
 */
@Repository
public interface GoodsinfoDAO extends MyBatisBaseDao<Goodsinfo, Integer> {
}