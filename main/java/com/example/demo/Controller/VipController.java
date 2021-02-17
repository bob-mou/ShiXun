package com.example.demo.Controller;

import com.example.demo.Entity.Vip;
import com.example.demo.Entity.VocationInfo;
import com.example.demo.Service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("vip")
public class VipController {

    @Autowired
     private VipService vipService;

    @GetMapping("/index")
    public String listVipPage(Model model){
        List<Vip> lists = vipService.findAllVip();
        model.addAttribute("vip",lists);
        return "vip_ht";
    }

    @GetMapping("/delv/{vip_id}")
    public String delv(@PathVariable("vip_id") Integer vid) {
        vipService.deleteVipByID(vid);
        return "redirect:/vip/index";
    }

    @GetMapping("/updatev/{vip_id}")
    public String updatev(@PathVariable("vip_id") Integer vid,Model model){
        Vip vip =  vipService.findVipByID(vid);
        model.addAttribute("vip",vip);
        return "vip_update";
    }

    @PostMapping("/updatev")
    public String updatev(Vip vid){
        vipService.updateVip(vid);
        return "redirect:/vip/index";
    }

}
