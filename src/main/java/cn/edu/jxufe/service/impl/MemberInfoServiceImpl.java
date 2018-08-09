package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.MemberinfoDAO;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoServiceImpl implements MemberInfoService {
    @Autowired
    public MemberinfoDAO dao;

    @Override
    public Memberinfo login(String tel, String psw) {
        Memberinfo memberinfo = new Memberinfo();
        memberinfo.setMemberMobile(tel);
        memberinfo.setMemberPasswd(psw);
        return dao.findByParams(memberinfo).get(0);
    }

    @Override
    public List<Memberinfo> findByParams(Memberinfo memberinfo) {
        return null;
    }
}
