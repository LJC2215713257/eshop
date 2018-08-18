package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Searchinfo;

import java.util.Date;
import java.util.List;

public interface SearchService {
    //插入搜索记录到数据库中
    public int insertJiLu(String sname, Date searchtime,Integer mid);

    //历史记录
    public List<Searchinfo> showKey(Integer mid);
}
