package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Searchinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SearchinfoDAO继承基类
 */
@Repository
public interface SearchinfoDAO extends MyBatisBaseDao<Searchinfo, Searchinfo> {
    List<Searchinfo> findkey(Integer mid);
}