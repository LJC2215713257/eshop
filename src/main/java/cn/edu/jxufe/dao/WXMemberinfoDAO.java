package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.Admin;
import cn.edu.jxufe.entity.WxMemberinfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WXMemberinfo继承基类
 */

/**
 * 测试
 */
@Repository
public interface WXMemberinfoDAO{
    int insertByParams(WxMemberinfo wxMemberinfo);
    List<WxMemberinfo> selectByParams(WxMemberinfo wxMemberinfo);
}