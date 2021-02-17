package com.example.demo.Controller;

import com.example.demo.Entity.SysLog;
import com.example.demo.Service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@RequestMapping("log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    //登录日志
    @RequestMapping("/login_log")
    public String getLoginPage(@RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex,
                               @RequestParam(value="pageSize",defaultValue = "11") Integer pageSize,
                               @RequestParam(value="account",required=false,defaultValue = "") String account,
                               Model model,
                               HttpSession session){
        PageInfo<SysLog> listInfo = null;
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if(account.isEmpty()){
            listInfo=sysLogService.findAllSysLog(pageIndex,pageSize);
        }if(!account.isEmpty()){
            //按照账号查找登录日志
            listInfo=sysLogService.findSysLogByAccount(pageIndex,pageSize,account);
        }
        String[] string=new String[]{"","普通用户","HR","普通管理员","高级管理员"};
        model.addAttribute("str", string);
        model.addAttribute("account",account);
        model.addAttribute("sys", listInfo);
        return "login_log";
    }
}
