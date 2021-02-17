package com.example.demo.Controller;



import com.example.demo.Entity.SubscribeInfo;
import com.example.demo.Service.SubscribeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("subscribe")
public class SubscribeInfoController {
    @Autowired
    private SubscribeInfoService subscribeInfoService;
    //管理员进入预约信息的页面(后台)
    @GetMapping("/index")
    public String listOrderPage(@RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex,
                                @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize,
                                Model model, HttpSession session) {
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        PageInfo<SubscribeInfo> lists = subscribeInfoService.findAllSubscribeInfo(pageIndex,pageSize);
        model.addAttribute("sub", lists);
        return "subscribeInfo";
    }
    //管理员对预约信息的查看(后台)
    @GetMapping("/delm/{subscribe_id}")
    public String delm(@PathVariable("subscribe_id")  Integer subscribe_id,HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        subscribeInfoService.deleteSubscribeInfoByID(subscribe_id);
        return "redirect:/subscribe/index";
    }
}
