package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AdminDAO继承基类
 */

/**
 * 测试
 */
@Repository
public interface AdminDAO extends MyBatisBaseDao<Admin, Admin> {
    int insertSelective();
    List<Admin> selectAll();
}