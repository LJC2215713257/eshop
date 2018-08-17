package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.MemberaddressDAO;
import cn.edu.jxufe.entity.Memberaddress;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private MemberaddressDAO dao;

    @Override
    public Memberaddress findOneByMid(int mid) {
        Memberaddress add = new Memberaddress();
        add.setMemberid(mid);
        List<Memberaddress> al = dao.findByParams(add);
        if(!al.isEmpty()) {
            return al.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int insertBySelective(Memberaddress addr) {
        return dao.insertSelective(addr);
    }
}
