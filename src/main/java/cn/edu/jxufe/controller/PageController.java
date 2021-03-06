package cn.edu.jxufe.controller;

import cn.edu.jxufe.bean.Message;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.entity.Orderinfo;
import cn.edu.jxufe.service.AdvertisementService;
import cn.edu.jxufe.service.CommentService;
import cn.edu.jxufe.service.GoodsInfoService;
import cn.edu.jxufe.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @Autowired
    private GoodsInfoService goodsService;
    @Autowired
    private AdvertisementService advService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "index")
    public String index(ModelMap map,HttpSession session){
        map.put("goodslist",goodsService.findByPage(1,10));
        map.put("advs",advService.findOnLineAdv());
        if(session.getAttribute("orderNum")==null){
            session.setAttribute("orderNum",0);
        }
        map.put("orderNum",session.getAttribute("orderNum"));
        map.put("location",session.getAttribute("location"));
        return "index";
    }

    @RequestMapping(value = "user")
    public String userPage(HttpSession session){
        if(session.getAttribute("user")==null){
            return "login";
        }else {
            return "forward:user/info";
        }
    }

    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "register")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "find_pwd")
    public String findPwd(){
        return "find_pwd";
    }

    @RequestMapping(value = "change_name")
    public String changeName(HttpSession session,ModelMap map){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null){
            map.put("name",user.getMemberName());
        }
        return "change_name";
    }

    @RequestMapping(value = "change_tel")
    public String changeTel(HttpSession session,ModelMap map){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null){
            map.put("tel",user.getMemberMobile());
        }
        return "change_tel";
    }

    @RequestMapping(value = "works")
    public String pageWorks(){
        return "upload_file";
    }

    @RequestMapping(value = "search")
    public String search(ModelMap map,HttpSession session){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null) {
            map.put("slist", searchService.showKey(user.getMemberId()));
        }
        return "search";
    }

    @RequestMapping(value = "comment")
    public String comment(ModelMap map,HttpSession session){
        Integer gid = (Integer) session.getAttribute("gid");
        System.out.println(gid);
        if(gid!=null) {
            map.put("clist", commentService.showContent(gid));
        }
        return "comment";
    }

    @RequestMapping(value = "cart")
    public String opneCart(HttpSession session,ModelMap map){
        Orderinfo cart = (Orderinfo) session.getAttribute("cart");
        if(cart!=null){
            map.put("cart",cart);
            if(session.getAttribute("orderNum")==null){
                session.setAttribute("orderNum",0);
            }
            map.put("orderNum",session.getAttribute("orderNum"));
        }
        return "cart";
    }


}
