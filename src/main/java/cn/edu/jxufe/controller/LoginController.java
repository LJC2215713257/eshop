package cn.edu.jxufe.controller;

import cn.edu.jxufe.bean.Message;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.MemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

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
                session.setAttribute("orderNum",0);
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

    @RequestMapping(value = "info")
    public String showInfo(ModelMap map,HttpSession session){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        System.out.println("forward info");
        if(user!=null){
            map.put("pic",user.getMemberPic());
            map.put("name",user.getMemberName());
            return "user";
        }else{
            return "login";
        }

    }

    @RequestMapping(value = "user_set")
    public String setUser(){
        return "user_set";
    }

    @RequestMapping(value = "relogin")
    public String reLogin(HttpSession session){
        if(session.getAttribute("user")!=null) {
            session.removeAttribute("user");
        }
        return "forward:/login";
    }

    @RequestMapping(value = "change_pwd")
    public String changPwdPage(){
        return "change_pwd";
    }

    @RequestMapping(value = "up_pwd")
    @ResponseBody
    public Message updatePwdOp(String old_p,String new_p,String check_p,HttpSession session){
        Message msg = new Message();
        Memberinfo memberinfo = (Memberinfo) session.getAttribute("user");
        if(memberinfo!=null){
            if(old_p.equals(memberinfo.getMemberPasswd())){
                if(new_p.isEmpty()){
                    msg.setTitle("-1");
                    msg.setEntity("密码或确认密码为空！");
                }else {
                    if (check_p.equals(new_p)) {
                        if(memberinfo.getMemberPasswd().equals(old_p)) {
                            Memberinfo temp = new Memberinfo();
                            temp.setMemberId(memberinfo.getMemberId());
                            temp.setMemberPasswd(new_p);
                            memberInfoService.updataBySelective(temp);
                            memberinfo.setMemberPasswd(new_p);
                            msg.setTitle("1");
                            msg.setEntity("success");
                        }else{
                            msg.setTitle("-2");
                            msg.setEntity("旧密码错误！");
                        }
                    } else {
                        msg.setTitle("-1");
                        msg.setEntity("密码和确认密码不一致！");
                    }
                }
            }else{
                msg.setTitle("2");
                msg.setEntity("旧密码错误！");
            }
        }else{
            msg.setTitle("0");
            msg.setEntity("用户未登录！");
        }
        return msg;
    }

    @RequestMapping(value = "article")
    public String articlePage(){
        return "article";
    }

    @RequestMapping(value = "profile")
    public String profilePage(HttpSession session,ModelMap map){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null) {
            map.put("name",user.getMemberName());
            map.put("tel",user.getMemberMobile());
            return "profile";
        }else{
            return "login";
        }
    }

    @RequestMapping(value = "getKey")
    @ResponseBody
    public Message getKey(String tel,HttpSession session){
        Message msg = new Message();
        Pattern pattern = Pattern.compile("[0-9]{11}");
        if(pattern.matcher(tel).matches()){
            if(memberInfoService.isTelUserful(tel)){
                String k = "";
                k+=(int)((Math.random()*9+1)*100000);
                if(session.getAttribute("reg_key")==null){
                    session.setAttribute("reg_key",k);
                }else{
                    session.removeAttribute("reg_key");
                    session.setAttribute("reg_key",k);
                }
                System.out.println("验证码："+k);
                msg.setTitle("1");
                msg.setEntity("success");
            }else{
                msg.setTitle("4");
                msg.setEntity("手机号已注册！");
            }
        }else{
            msg.setTitle("0");
            msg.setEntity("不匹配的手机号格式！");
        }
        return  msg;
    }

    @RequestMapping(value = "updateTel")
    @ResponseBody
    public Message updateTel(String tel,HttpSession session){
        Message msg = new Message();
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        Pattern pattern = Pattern.compile("[0-9]{11}");
        msg.setTitle("-1");
        if(user!=null) {
            if (pattern.matcher(tel).matches()) {
                if (memberInfoService.isTelUserful(tel)) {
                    Memberinfo temp = new Memberinfo();
                    temp.setMemberId(user.getMemberId());
                    temp.setMemberMobile(tel);
                    msg.setTitle(memberInfoService.updataBySelective(temp) + "");
                    user.setMemberMobile(tel);
                    msg.setEntity("success");
                } else {
                    msg.setTitle("4");
                    msg.setEntity("手机号已注册！");
                }
            } else {
                msg.setTitle("0");
                msg.setEntity("不匹配的手机号格式！");
            }
        }
        return  msg;
    }

    @RequestMapping(value = "updateName")
    @ResponseBody
    public Message updateName(String name,HttpSession session){
        Message msg = new Message();
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        //Pattern pattern = Pattern.compile("[0-9]{11}");
        msg.setTitle("-1");
        if(user!=null) {
                if (memberInfoService.isNameUserful(name)) {
                    Memberinfo temp = new Memberinfo();
                    temp.setMemberId(user.getMemberId());
                    temp.setMemberName(name);
                    msg.setTitle(memberInfoService.updataBySelective(temp) + "");
                    msg.setEntity("success");
                    user.setMemberName(name);
                } else {
                    msg.setTitle("4");
                    msg.setEntity("昵称已被使用！");
                }
        } else {
            msg.setTitle("0");
            msg.setEntity("用户未登录！");
        }
        return  msg;
    }

    @RequestMapping(value = "getFindKey")
    @ResponseBody
    public Message getFindKey(String tel,HttpSession session){
        Message msg = new Message();
        Pattern pattern = Pattern.compile("[0-9]{11}");
        if(pattern.matcher(tel).matches()){
            if(!memberInfoService.isTelUserful(tel)){
                String k = "";
                k+=(int)((Math.random()*9+1)*100000);
                if(session.getAttribute("find_key")==null){
                    session.setAttribute("find_key",k);
                }else{
                    session.removeAttribute("find_key");
                    session.setAttribute("find_key",k);
                }
                System.out.println("验证码："+k);
                msg.setTitle("1");
                msg.setEntity("success");
            }else{
                msg.setTitle("4");
                msg.setEntity("该手机号未注册账号！");
            }
        }else{
            msg.setTitle("0");
            msg.setEntity("不匹配的手机号格式！");
        }
        return  msg;
    }

    @RequestMapping(value = "findpwd")
    @ResponseBody
    public Message findPwd(String tel,String k,String new_p,String check_p,HttpSession session){
        Message msg = new Message();
        Pattern pattern = Pattern.compile("[0-9]{11}");
        if(pattern.matcher(tel).matches()){
            if(!memberInfoService.isTelUserful(tel)) {
                if (!new_p.isEmpty()) {
                    if (new_p.equals(check_p)) {
                        if (((String) session.getAttribute("find_key")).equals(k)) {
                            session.removeAttribute("find_key");
                            Memberinfo temp = new Memberinfo();
                            temp.setMemberMobile(tel);
                            Memberinfo memberinfo = memberInfoService.findByParams(temp).get(0);
                            memberinfo.setMemberPasswd(new_p);
                            Memberinfo men_tep = new Memberinfo();
                            men_tep.setMemberId(memberinfo.getMemberId());
                            men_tep.setMemberPasswd(new_p);
                            memberInfoService.updataBySelective(men_tep);
                            if(session.getAttribute("user")!=null){
                                session.removeAttribute("user");
                            }
                            session.setAttribute("user",memberinfo);
                            msg.setTitle("1");
                            msg.setEntity("验证成功！");
                        } else {
                            msg.setTitle("5");
                            msg.setEntity("验证码不正确！");
                        }
                        System.out.println("验证码：" + k);
                    } else {
                        msg.setTitle("6");
                        msg.setEntity("密码和确认密码不一致！");
                    }

                } else {
                    msg.setTitle("6");
                    msg.setEntity("密码不能为空！");
                }
            }else{
                msg.setTitle("4");
                msg.setEntity("该手机号未注册账号！");
            }
        }else{
            msg.setTitle("0");
            msg.setEntity("不匹配的手机号格式！");
        }
        return  msg;
    }

    @RequestMapping(value = "register")
    @ResponseBody
    public Message register(String tel, String k, HttpSession session, HttpServletRequest request){
        Message msg = new Message();
        Pattern pattern = Pattern.compile("[0-9]{11}");
        if(pattern.matcher(tel).matches()){
            if(memberInfoService.isTelUserful(tel)){
                if(((String)session.getAttribute("reg_key"))!=null&&((String)session.getAttribute("reg_key")).equals(k)){
                    session.removeAttribute("reg_key");
                    Memberinfo m = new Memberinfo();
                    m.setMemberMobile(tel);
                    m.setMemberPasswd("123456");
                    m.setCreateTime(new Date());
                    System.out.println("创建日期："+m.getCreateTime());
                    m.setMemberName("233");
                    m.setMemberLoginIp(request.getRemoteAddr());
                    m.setMemberPic("DefaultAvatar.jpg");
                    memberInfoService.register(m);
                    msg.setTitle("1");
                    msg.setEntity("验证成功！");
                }else{
                    msg.setTitle("5");
                    msg.setEntity("验证码不正确！");
                }
                System.out.println("验证码："+k);

            }else{
                msg.setTitle("4");
                msg.setEntity("手机号已注册！");
            }
        }else{
            msg.setTitle("0");
            msg.setEntity("不匹配的手机号格式！");
        }
        return  msg;
    }
}
