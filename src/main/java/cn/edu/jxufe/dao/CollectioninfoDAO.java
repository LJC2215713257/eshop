package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Collectioninfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CollectioninfoDAO继承基类
 */
@Repository
public interface CollectioninfoDAO extends MyBatisBaseDao<Collectioninfo, Integer> {
    List<Integer> findByMid(int mid);
}