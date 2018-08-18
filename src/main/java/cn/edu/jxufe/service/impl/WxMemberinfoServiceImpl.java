package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.MemberinfoDAO;
import cn.edu.jxufe.dao.WXMemberinfoDAO;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.entity.WxMemberinfo;
import cn.edu.jxufe.service.WxMemberinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WxMemberinfoServiceImpl implements WxMemberinfoService {
    @Autowired
    private WXMemberinfoDAO wxMemberinfodao;
    @Autowired
    private MemberinfoDAO memberinfoDAO;

    @Override
    public Memberinfo AuthorizedLogin(WxMemberinfo wxMemberinfo) {
        System.out.println(wxMemberinfo.toString());
        Memberinfo usertemp = new Memberinfo();
        usertemp.setCreateTime(new Date());
        usertemp.setMemberName(wxMemberinfo.getNickname());
        usertemp.setMemberLoginNum(1);
        usertemp.setMemberWw(wxMemberinfo.getOpenid());
        usertemp.setMemberPic(wxMemberinfo.getHeadimgurl());
        usertemp.setMemberLoginTime(usertemp.getCreateTime());
        usertemp.setMemberMobile("12345678912");
        List<WxMemberinfo> ws = wxMemberinfodao.selectByParams(wxMemberinfo);
        if(ws.isEmpty()) {
            wxMemberinfodao.insertByParams(wxMemberinfo);
        }
        Memberinfo temp = new Memberinfo();
        temp.setMemberWw(wxMemberinfo.getOpenid());
        List<Memberinfo> ms = memberinfoDAO.findByParams(temp);
        if (!ms.isEmpty()) {
            return ms.get(0);
        }
        memberinfoDAO.insertSelective(usertemp);
        return memberinfoDAO.findByParams(temp).get(0);
    }
}
