package com.example.demo.Controller;

import com.example.demo.Entity.MonthOrder;
import com.example.demo.Service.HrService;
import com.example.demo.Service.ResumeInfoService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("data")
public class DateAnalysisController {


    @Autowired
    private ResumeInfoService resumeInfoService;
    @Autowired
    private HrService hrService;
    @Autowired
    private UserService userService;

    //index页面返回lists中查询的值（测试使用）
    @GetMapping("num")
    public String getNum(Model model,
                         HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        Integer reNum=resumeInfoService.countAllResume();
        Integer hrNum = hrService.countHr();
        Integer userNum = userService.count();
        model.addAttribute("reNum",reNum);
        model.addAttribute("hrNum",hrNum);
        model.addAttribute("userNum",userNum);
        return "index_ht";
    }
}
