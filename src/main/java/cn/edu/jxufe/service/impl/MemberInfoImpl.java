package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.MemberinfoDAO;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInfoImpl implements MemberInfoService {

    @Autowired
    MemberinfoDAO memberinfoDAO;


    @Override
    public int isTrue(String tel, String pwd) {
        List<Memberinfo> list=  memberinfoDAO.findUser(tel,pwd);
        if(list.isEmpty()){
            return 0;
        }
        else {
            return 1;
        }
    }
}
