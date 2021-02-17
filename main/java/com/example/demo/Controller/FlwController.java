package com.example.demo.Controller;

import com.example.demo.Entity.Flw;
import com.example.demo.Service.FlwInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("flw")

public class FlwController {
    @Autowired
    private FlwInfoService flwInfoService;

    //订单查询
    @GetMapping("/index")
    public String getFlwPage(Model model){
       List<Flw> flw =flwInfoService.findAllFlw();
       model.addAttribute("flw",flw);
        return "order_ht";
    }

    //订单删除
    @GetMapping("delo")
    public String delFlw(Integer id){
        flwInfoService.delFlw(id);
        return "redirect:/flw/index";
    }

}
