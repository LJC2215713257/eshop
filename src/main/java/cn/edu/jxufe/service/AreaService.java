package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Areainfo;

import java.util.List;

public interface AreaService {
    List<Areainfo> findByAreaDeep(Integer pid,int deep);
    Integer findByPName(String areaName);
    List<String> findHotCity();
    List<String> findByZimu(String zimu);
}
