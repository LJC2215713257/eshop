package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.GoodsCategoryDAO;
import cn.edu.jxufe.entity.GoodsCategory;
import cn.edu.jxufe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private GoodsCategoryDAO dao;

    @Override
    public List<GoodsCategory> findAll() {
        return dao.findAll();
    }
}
