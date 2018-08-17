package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Memberinfo;

import java.util.List;

public interface MemberInfoService {
    Memberinfo login(String tel,String psw);
    int register(Memberinfo memberinfo);
    List<Memberinfo> findByParams(Memberinfo memberinfo);
    boolean isTelUserful(String tel);
    boolean isNameUserful(String name);
    int updataBySelective(Memberinfo memberinfo);
    Integer getMId(String mname);
}
