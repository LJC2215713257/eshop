package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.CollectioninfoDAO;
import cn.edu.jxufe.entity.Collectioninfo;
import cn.edu.jxufe.service.CollectionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectioninfoDAO dao;

    @Override
    public List<Integer> findByPage(int mid) {
        List<Integer> ls = dao.findByMid(mid);
        return ls;
    }
}
