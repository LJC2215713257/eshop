package cn.edu.jxufe.controller;

import cn.edu.jxufe.bean.Message;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequestMapping(value = "user")
@Controller
public class LoginController {

    @Autowired
    MemberInfoService memberInfoService;

    @RequestMapping("login")
    @ResponseBody
    public Message login(String tel, String psw, HttpSession session){
        System.out.println("处理一个登陆的请求");
        System.out.println("传入的账号是"+tel);
        System.out.println("传入的密码是"+psw);
        Message message = new Message();
        message.setTitle("0");
        if(tel!=null&&psw!=null&&!tel.isEmpty()&&!psw.isEmpty()) {
            System.out.println("enter login controller");
            Memberinfo m = memberInfoService.login(tel, psw);
            if (m != null) {
                System.out.println("登陆成功");
                System.out.println(m.getMemberId());
                session.removeAttribute("user");
                session.setAttribute("user", m);
                message.setTitle("1");
                return message;
            } else {
                System.out.println("登陆失败");
                message.setTitle("0");
                return message;
            }
        }
        message.setTitle("0");
        return message;
    }


    @RequestMapping(value = "register")
    public String register(){
        return "index";
    }
}
