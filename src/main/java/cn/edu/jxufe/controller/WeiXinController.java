package cn.edu.jxufe.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import cn.edu.jxufe.entity.WxMemberinfo;
import cn.edu.jxufe.service.WxMemberinfoService;
import cn.edu.jxufe.util.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class WeiXinController {
    @Autowired
    private WxMemberinfoService wxmemberinfoService;

    @Value("${appID}")
    private String appID;
    @Value("${appsecret}")
    private String appsecret;


    @RequestMapping(value = "weixin")
    public Object weixin(@RequestParam(name="code",defaultValue = "") String code, HttpSession session){
        System.out.println("code:"+code);
        String res = HttpUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                +appID+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code");
        JSONObject obj = JSON.parseObject(res);
        String res1 = HttpUtils.doGet("https://api.weixin.qq.com/sns/userinfo?access_token="+
                obj.getString("access_token")+"&openid="+obj.getString("openid")+"&lang=zh_CN");

        WxMemberinfo wx = JSON.parseObject(res1,WxMemberinfo.class);
        session.setAttribute("user",wxmemberinfoService.AuthorizedLogin(wx));
        return "forward:user";
    }

    @RequestMapping(value = "reSendWx")
    @ResponseBody
    public String reSendWx(){
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                +appID+"&redirect_uri=http://t7y8np.natappfree.cc/weixin&" +
                "response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    }

    @RequestMapping(value = "wxlogin")
    public String wxLogin(){
        return "wx_login";
    }
}
