package cn.edu.jxufe.controller;

import cn.edu.jxufe.entity.Goodsinfo;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.CollectionService;
import cn.edu.jxufe.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "col")
public class CollectionController {
    @Autowired
    private GoodsInfoService goodsservice;
    @Autowired
    private CollectionService colservice;


    @RequestMapping(value = "favorite")
    public String collection(HttpSession session, @RequestParam(name = "page",defaultValue = "1") int page, ModelMap map){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null&&user.getMemberId()!=null){
//            List<Integer> gids = colservice.findByPage(user.getMemberId());
//            System.out.println("size :"+gids.size());
//            if(!gids.isEmpty()) {
//                List<Goodsinfo> gls = goodsservice.findByGoodsIdSet(gids);
//                map.put("goodsls",gls);
//            }
            map.put("goodsls",goodsservice.findCollect(user.getMemberId()));
            return "favorite";
        }else{
            return "login";
        }
    }
}
