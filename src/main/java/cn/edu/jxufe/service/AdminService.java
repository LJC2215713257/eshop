package cn.edu.jxufe.service;

import cn.edu.jxufe.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
    List<Admin> findAll();
}
