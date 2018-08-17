package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Memberaddress;

public interface AddressService {
    Memberaddress findOneByMid(int mid);
    int insertBySelective(Memberaddress addr);
}
