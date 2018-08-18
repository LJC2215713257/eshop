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
    public int register(Memberinfo memberinfo) {
        return dao.insertSelective(memberinfo);
    }

    @Override
    public List<Memberinfo> findByParams(Memberinfo memberinfo) {
        return dao.findByParams(memberinfo);
    }

    @Override
    public boolean isTelUserful(String tel) {
        Memberinfo m = new Memberinfo();
        m.setMemberMobile(tel);
        List<Memberinfo> i = dao.findByParams(m);
        if(i.size()>0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean isNameUserful(String name) {
        Memberinfo m = new Memberinfo();
        m.setMemberName(name);
        List<Memberinfo> i = dao.findByParams(m);
        if(i.size()>0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public int updataBySelective(Memberinfo memberinfo) {
        return dao.updateByPrimaryKeySelective(memberinfo);
    }

    @Override
    public Integer getMId(String mname) {
        return dao.findIdByName(mname);
    }

    @Override
    public String findNameByMid(Integer mid) {
        if(mid!=null) {
            return dao.findById(mid);
        }
        return null;
    }
}
