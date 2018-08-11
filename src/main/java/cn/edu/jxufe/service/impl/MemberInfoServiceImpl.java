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
        List<Memberinfo> ms = dao.findByParams(memberinfo);
        if(ms.size()!=0&&ms!=null) {
            return ms.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Memberinfo> findByParams(Memberinfo memberinfo) {
        return null;
    }
}
