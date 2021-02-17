package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Mapper.PublishListMapper;
import com.example.demo.Service.PublishListService;
import com.example.demo.Service.ResumeInfoService;
import com.example.demo.Service.SubscribeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("publishList")
public class PublishListController {
    @Autowired
    private PublishListService publishListService;
    @Autowired
    private ResumeInfoService resumeInfoService;

    @Autowired
    private SubscribeInfoService subscribeInfoService;

    @GetMapping("/index")//所有未处理
    public String listPage(Model model,HttpSession session) {
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        List<PublishList> lists = publishListService.findAll();
        model.addAttribute("publishlist", lists);
        Integer countDealWith= subscribeInfoService.countDealWith();
        Integer countResume=resumeInfoService.countResume();
        Integer countAll =countDealWith+countResume;
        session.setAttribute("countDealWith",countDealWith);
        session.setAttribute("countResume",countResume);
        session.setAttribute("countAll",countAll);
        return "resume_publish";
    }

    @PostMapping("/search")
    public String searchPage(@RequestParam(name="isdealwith") Boolean isdealwith,
                             Model model,
                             HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if(isdealwith==true){
            //查询所有已处理
            List<PublishList> lists=publishListService.findAllApply();
            model.addAttribute("publishlist", lists);
        }else if(isdealwith==false){
            //所有的未处理
            List<PublishList> lists=publishListService.findAll();
            model.addAttribute("publishlist", lists);
        }else{
            return "redirect:/publishList/index";}
        return "resume_publish";
    }
    //将正在编辑申请表的ID号放在session中
    //审核简历信息
    @PostMapping("/check")
    public String check(@RequestParam(name="resume_id",required=false) Integer rid,
                        @RequestParam(name="apply_for_publish_ID",required=false) Integer apply_for_publish_ID,
                        Model model,
                        HttpSession session){
        //根据简历号resume_id查找简历信息，并保存到session
        //根据申请发布号查看申请发布信息，并保存到session
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        ResumeInfo resumeinfo=resumeInfoService.findAllResumeInfoByID(rid);
        PublishList publishlist=publishListService.findApplyByID(apply_for_publish_ID);
        session.setAttribute("apply_for_publish_ID",apply_for_publish_ID);
        session.setAttribute("resumeinfo",resumeinfo);
        model.addAttribute("resumeinfo",resumeinfo);
        model.addAttribute("publishlist",publishlist);
        return "resume_publish_check";
    }

    //需要管理员登录时将管理员信息保存到Session中
    //通过信息（是否批准发布、审批意见、管理员操作人、审批时间）就记录在apply_for_publishList表中
    //简历发布信息记录在简历信息表中
    //返回简历申请界面
    @PostMapping("/submit")
    public String submit(@RequestParam(name="isPublished",required=false) boolean isPublished,
                         @RequestParam(name="apply_message",required=false) String apply_message,
                         HttpServletRequest request,
                         HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        Date date =new Date();//获取当前时间（审批时间）
        Integer apply_for_publish_ID=(Integer)request.getSession().getAttribute("apply_for_publish_ID");
        Manager manager= (Manager) session.getAttribute("manager");
        Integer manager_id=manager.getMid();
        java.sql.Date d= new java.sql.Date(date.getTime());
        PublishList publishlist= publishListService.findApplyByID(apply_for_publish_ID);
        publishlist.setApply_message(apply_message);
        publishlist.setPublished(isPublished);
        publishlist.setResume_publish_date(d);
        publishlist.setManager_id(manager_id);
        publishlist.setIsdealwith(true);
        publishListService.updatePublishList(publishlist);
        ResumeInfo resumeinfo = (ResumeInfo) session.getAttribute("resumeinfo");
        if(isPublished){
            resumeinfo.setState("已发布");
            resumeinfo.setResume_publish_date(d);
        }else{
            resumeinfo.setState("未发布");
        }
        resumeInfoService.updatestate(resumeinfo);
        session.removeAttribute("resumeinfo");
        return "redirect:/publishList/index";
    }

}
