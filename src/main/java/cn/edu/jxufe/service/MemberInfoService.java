package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Memberinfo;

import java.util.List;

public interface MemberInfoService {
    Memberinfo login(String tel,String psw);
    List<Memberinfo> findByParams(Memberinfo memberinfo);

}
