package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.GoodsComment;
import cn.edu.jxufe.entity.Memberinfo;

import java.util.List;

public interface CommentService {
    //将评论内容插入数据库中
    public int insertComment(int goodisId, String com, Memberinfo user);
    //显示评论内容
    public List<GoodsComment> showContent(int goodisId);
}
