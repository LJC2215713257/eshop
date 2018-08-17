package cn.edu.jxufe.dao;

import cn.edu.jxufe.entity.GoodsComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GoodsCommentDAO继承基类
 */
@Repository
public interface GoodsCommentDAO extends MyBatisBaseDao<GoodsComment, Integer> {
    List<GoodsComment> findcontent(int goodisId);
}