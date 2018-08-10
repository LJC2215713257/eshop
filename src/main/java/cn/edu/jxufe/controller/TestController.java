package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.Admin;
import cn.edu.jxufe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    private AdminService service;

    @RequestMapping("test")
    @ResponseBody
    public List<Admin> findAll(){
        return service.findAll();
    }
}
