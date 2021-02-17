package com.example.demo.Controller;



import com.example.demo.Entity.*;
import com.example.demo.Service.VocationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("vocation")
public class VocationController {

    @Autowired
    private VocationService vocationService;
    //行业信息表的增删改查
    @GetMapping("/index")
    public String listVocPage(@RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex,
                              @RequestParam(value="pageSize",defaultValue = "8") Integer pageSize,
                              Model model,HttpSession session) {
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        PageInfo<VocationInfo> lists = vocationService.findAllVocFY(pageIndex,pageSize);
        model.addAttribute("vocationinfo", lists);
        return "vocation_display";
    }

    @GetMapping("/delv/{vid}")
    public String delv(@PathVariable("vid") Integer vocation_id,HttpSession session) {
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        vocationService.deleteVocationByID(vocation_id);
        return "redirect:/vocation/index";
    }

    @GetMapping("/addv")
    public String addv(HttpSession session) {
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        return "vocation_add";
    }
    @PostMapping("/addv")
    public String addv(@RequestParam(name="vocation_name",required=false) String vocation_name,
                       HttpSession session) {
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if (!vocation_name.isEmpty()) {
        vocationService.insertVocation(vocation_name);
        }
        return "redirect:/vocation/index";
    }
    @GetMapping("/updatev/{vid}")
    public String updatev(@PathVariable("vid")  Integer vocation_id,
                          Model model,
                          HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht"; }
        VocationInfo vocationinfo=vocationService.findVocationByID(vocation_id);
        model.addAttribute("vocation",vocationinfo);
        return "vocation_update";
    }
    @PostMapping("/updatev")
    public String updatev(@RequestParam(name="vocation_name",required=false) String vocation_name,
                           @RequestParam(name="vocation_id",required=false) Integer vocation_id,
                          HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht"; }
        if(vocation_id!=null&&vocation_name!=null){
        VocationInfo vocationinfo=vocationService.findVocationByID(vocation_id);
        vocationinfo.setVocation_name(vocation_name);
        vocationService.updateVocation(vocationinfo);
        }
        return "redirect:/vocation/index";
    }
}
