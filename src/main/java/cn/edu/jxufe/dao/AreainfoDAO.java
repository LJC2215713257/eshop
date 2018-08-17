package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Areainfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * AreainfoDAO继承基类
 */
@Repository
public interface AreainfoDAO extends MyBatisBaseDao<Areainfo, Integer> {
    List<Areainfo> findByAreaDeep(Map map);
    Integer findByPName(String areaName);
    List<String> findHotCity();
    List<String> findCityByZi(String zimu);
}