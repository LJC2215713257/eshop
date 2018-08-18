package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.GoodsCommentDAO;
import cn.edu.jxufe.entity.GoodsComment;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private GoodsCommentDAO commentDAO;
    @Override
    public int insertComment(int goodisId, String com, Memberinfo user) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        GoodsComment g=new GoodsComment();
        g.setGoodisId(goodisId);
        g.setScommContent(com);
        g.setScommTime(df.format(new Date()));
        g.setScommMemberid(user.getMemberId());
        g.setScommMemberavatar(user.getMemberPic());
        g.setScommMembername(user.getMemberName());
        return commentDAO.insertSelective(g);
    }

    @Override
    public List<GoodsComment> showContent(int goodisId) {
        return commentDAO.findcontent(goodisId);
    }


}
