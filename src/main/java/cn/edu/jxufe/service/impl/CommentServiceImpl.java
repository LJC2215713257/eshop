package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.GoodsCommentDAO;
import cn.edu.jxufe.entity.GoodsComment;
import cn.edu.jxufe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private GoodsCommentDAO commentDAO;
    @Override
    public int insertComment(int goodisId,String com,String scommTime) {
        GoodsComment g=new GoodsComment();
        g.setGoodisId(goodisId);
        g.setScommContent(com);
        g.setScommTime(scommTime);
        return commentDAO.insertSelective(g);
    }

    @Override
    public List<GoodsComment> showContent(int goodisId) {
        return commentDAO.findcontent(goodisId);
    }


}
