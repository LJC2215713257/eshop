package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.AreainfoDAO;
import cn.edu.jxufe.entity.Areainfo;
import cn.edu.jxufe.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreainfoDAO dao;

    @Override
    public List<Areainfo> findByAreaDeep(Integer pid,int deep) {
        Map map = new HashMap();
        if(pid==null){
            map.put("parentid",null);
            map.put("deep",deep);

        }else{
            map.put("parentid",pid);
            map.put("deep",deep);
        }
        return dao.findByAreaDeep(map);
    }

    @Override
    public Integer findByPName(String areaName) {
        return dao.findByPName(areaName);
    }

    @Override
    public List<String> findHotCity() {
        return dao.findHotCity();
    }

    @Override
    public List<String> findByZimu(String zimu) {
        return dao.findCityByZi(zimu);
    }
}
