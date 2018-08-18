package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Memberinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MemberinfoDAO继承基类
 */
@Repository
public interface MemberinfoDAO extends MyBatisBaseDao<Memberinfo, Integer> {

    //判断是否存在
    int updateByPrimaryKeySelective(Memberinfo memberinfo);
    public List<Memberinfo> findUser(String tel,String pwd);
    //通过名字查询id
    public int findIdByName(String mname);
    public String findById(Integer mid);
}