package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.SearchinfoDAO;
import cn.edu.jxufe.entity.Searchinfo;
import cn.edu.jxufe.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchinfoDAO searchinfoDAO;
    @Override
    public int insertJiLu(String sname,Date searchtime,Integer mid) {
        Searchinfo s = new Searchinfo();
        s.setSearchKey(sname);
        s.setSearchtime(searchtime);
        s.setMemberId(mid);
        return searchinfoDAO.insertSelective(s);
    }

    @Override
    public List<Searchinfo> showKey(Integer mid) {
        if (mid != null) {
            return searchinfoDAO.findkey(mid);
        }
        return null;
    }
}
