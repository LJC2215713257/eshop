package cn.edu.jxufe.controller;

import cn.edu.jxufe.bean.Message;
import cn.edu.jxufe.entity.Areainfo;
import cn.edu.jxufe.entity.Memberaddress;
import cn.edu.jxufe.entity.Memberinfo;
import cn.edu.jxufe.service.AddressService;
import cn.edu.jxufe.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@RequestMapping(value = "area")
@Controller
public class AddressController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private AddressService addressService;


    @RequestMapping(value = "arealist")
    @ResponseBody
    public List<Areainfo> findAreas(@RequestParam(name = "pname",defaultValue = "null") String pname,int deep){
        Integer pid = areaService.findByPName(pname);
        return areaService.findByAreaDeep(pid,deep);
    }

    @RequestMapping(value = "edit_addr")
    public String openAddrPage(HttpSession session, ModelMap map){
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null){
            map.put("addr",addressService.findOneByMid(user.getMemberId()));
            map.put("pros",areaService.findByAreaDeep(null,1));
            return "address";
        }
        return "login";
    }

    @RequestMapping(value = "commitaddr")
    @ResponseBody
    public Message commitAddr(Memberaddress madd,HttpSession session){
        System.out.println(madd);
        Message msg = new Message();
        msg.setTitle("0");
        Memberinfo user = (Memberinfo) session.getAttribute("user");
        if(user!=null){
            madd.setMemberid(user.getMemberId());
            addressService.insertBySelective(madd);
            msg.setTitle("1");
        }
        return msg;
    }

    @RequestMapping(value = "location")
    public String pageLocation(ModelMap map){
        map.put("hots",areaService.findHotCity());
        return "location";
    }

    @RequestMapping(value = "splist")
    @ResponseBody
    public List<String> getCityList(String zimu){
        if(zimu!=null){
            return areaService.findByZimu(zimu);
        }
        return null;
    }

    @RequestMapping(value = "updateLocation")
    @ResponseBody
    public void updateLocation(@RequestParam(name = "location") String location, HttpSession session){
        if(session.getAttribute("location")!=null){
            session.removeAttribute("location");
        }
        session.setAttribute("location",location);
    }
}
