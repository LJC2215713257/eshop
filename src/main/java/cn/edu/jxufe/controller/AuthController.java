package cn.edu.jxufe.controller;

import cn.edu.jxufe.util.SHA1;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.Arrays;

@Controller
public class AuthController {

    @Value("${token}")
    private String token;

    @RequestMapping(value = "comein", method =RequestMethod.GET)
    @ResponseBody
    public String comeIn(String signature,String timestamp,String nonce,String echostr){
        System.out.println("有个请求来了！");
        if(checkWx(timestamp,nonce).equals(signature)){
            return echostr;
        }
        return null;
    }

    @RequestMapping(value = "comein", method = RequestMethod.POST)
    public Object comeIn(String signature,String timestamp,String nonce,String echostr,@RequestBody String xml){
        if(checkWx(timestamp,nonce).equals(signature)){
            System.out.println(xml);
        }
        return null;
    }

    private String checkWx(String timestamp,String nonce){
        System.out.println(" "+timestamp+" "+nonce+" ");
        String info[] ={token,timestamp,nonce};
        Arrays.sort(info);
        String str = ""+info[0]+info[1]+info[2];
        System.out.println(str);
        String str1= SHA1.encode(str);
        System.out.println(str1);
        return str1;
    }
}
