package cn.edu.jxufe.service.impl;

import cn.edu.jxufe.dao.AdminDAO;
import cn.edu.jxufe.entity.Admin;
import cn.edu.jxufe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO dao;

    @Override
    public List<Admin> findAll() {
        return dao.findAll();
    }
}
