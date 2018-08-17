package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.entity.WxMemberinfo;

public interface WxMemberinfoService {
    Memberinfo AuthorizedLogin(WxMemberinfo wxMemberinfo);
}
