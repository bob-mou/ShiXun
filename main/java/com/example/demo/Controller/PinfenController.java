package com.example.demo.Controller;


import com.example.demo.Entity.Pingfen;
import com.example.demo.Service.PinfenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("pinfen")
public class PinfenController {
    @Autowired
    private PinfenService pinfenService;
    //进入评分页面（后台）
    @GetMapping("/index")
    public String indexPage(Model model1,HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        List<Pingfen> lists1 = pinfenService.findrPinfen();
        model1.addAttribute("rinfo", lists1);
        List<Pingfen> lists2= pinfenService.findHRPinfen();
        model1.addAttribute("hrinfo", lists2);
        return "pinfen";
    }

    //查询简历信息（后台）
    @PostMapping("/search_r")
    public String searchrPage(@RequestParam(name="name",required=false) String name,
                             @RequestParam(name="PF",required=false) Integer PF,Model model,HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if(name==null&&PF==null){
            return "redirect:/pinfen/index";
        }//按照名字查询简历
        else if(name!=null&&PF==null){
            List<Pingfen> lists1=pinfenService.findrPinfenByName(name);
            model.addAttribute("rinfo", lists1);
            model.addAttribute("name", name);
        }else if(name==null&&PF!=null){
            List<Pingfen> lists1=pinfenService.findrPinfenByPfmark(PF);
            model.addAttribute("rinfo", lists1);
            model.addAttribute("PF_r", PF);
        }else{
            Pingfen pingfen =new Pingfen();
            pingfen.setPingfenmark(PF);
            pingfen.setOldresumeName(name);
            List<Pingfen> lists1=pinfenService.findrPinfenByPfmarkandName(pingfen);
            model.addAttribute("rinfo", lists1);
            model.addAttribute("name", name);
            model.addAttribute("PF_r", PF);
        }
        List<Pingfen> lists2= pinfenService.findHRPinfen();
        model.addAttribute("hrinfo", lists2);
        return "pinfen";
    }

    //管理员查询HR信息
    @PostMapping("/search_hr")
    public String searchhrPage(@RequestParam(name="HRname",required=false) String HRname,
                             @RequestParam(name="PF",required=false) Integer PF,Model model,HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if(HRname==""&&PF==null){
            return "redirect:/pinfen/index";
        }//按照ID查找管理员
        else if(HRname!=""&&PF==null){
            List<Pingfen> lists2= pinfenService.findHRPinfenByName(HRname);
            model.addAttribute("hrinfo", lists2);
            model.addAttribute("HRname_hr", HRname);
        }else if(HRname==""&&PF!=null){
            List<Pingfen> lists2= pinfenService.findHRPinfenByPfmark(PF);
            model.addAttribute("hrinfo", lists2);
            model.addAttribute("PF_hr", PF);
        }else{
            List<Pingfen> lists2= pinfenService.findHRPinfenByPfmarkandName(HRname,PF);
            model.addAttribute("hrinfo", lists2);
            model.addAttribute("HRname_hr", HRname);
            model.addAttribute("PF_hr", PF);
        }
        List<Pingfen> lists1 = pinfenService.findrPinfen();
        model.addAttribute("rinfo", lists1);
        return "pinfen";
    }
}
