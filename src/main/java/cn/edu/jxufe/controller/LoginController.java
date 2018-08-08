package cn.edu.jxufe.controller;

import cn.edu.jxufe.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    MemberInfoService memberInfoService;

    @RequestMapping("login")
    public Object login(String tel,String pwd){
        System.out.println("处理一个登陆的请求");
        System.out.println("传入的账号是"+tel);
        System.out.println("传入的密码是"+pwd);
        int t=memberInfoService.isTrue(tel,pwd);
        if(t!=0){
            System.out.println("登陆成功");
            return "index";
        }
        else {
            System.out.println("登陆失败");
            return "login";
        }
    }

}
