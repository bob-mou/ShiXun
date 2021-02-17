package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Mapper.ResumeInfoMapper;
import com.example.demo.Service.*;
import com.example.demo.Utils.FileUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("ht")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private UserService userService;

    @Autowired
    private HrService hrService;
    @Autowired
    private SubscribeInfoService subscribeInfoService;

    @Autowired
    private ResumeInfoService resumeInfoService;
    //设置登录
    @GetMapping("/login_ht")
    public String getLoginPage(){
        return "login_ht";
    }
    //设置主页
    @GetMapping("/index")
    public String getIndexPage(Model model,
                               HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        Integer reNum=resumeInfoService.countResumeNum();
        Integer hrNum = hrService.countHr();
        Integer userNum = userService.count();
        model.addAttribute("reNum",reNum);
        model.addAttribute("hrNum",hrNum);
        model.addAttribute("userNum",userNum);
        //简历排行top10
        List reInfosOldName=new ArrayList();//简历名字列表
        List reInfoTimes=new ArrayList();//简历下载次数
        List<ResumeInfo> resumeInfos =resumeInfoService.finResumeByResumeTimes();
        for (int i = 0; i <= resumeInfos.size()-1; i++){
            ResumeInfo resumeInfo= resumeInfos.get(i);
            reInfosOldName.add(resumeInfo.getOldresumeName());
            reInfoTimes.add(resumeInfo.getResume_times());
        }
        model.addAttribute("nums",reInfoTimes);
        model.addAttribute("cate",reInfosOldName);
        //简历排行top10
        //---------职业排行top10
        List vocationName2=new ArrayList();
        List VocationTimes=new ArrayList();
        List<ResumeInfo> resumeInfos2=resumeInfoService.finResumeByVocationTimes();
        for (int i = 0; i <= resumeInfos2.size()-1; i++){
            ResumeInfo resume= resumeInfos2.get(i);
            vocationName2.add(resume.getVocation_name());
            VocationTimes.add(resume.getResume_times());
        }
        model.addAttribute("nums2",VocationTimes);
        model.addAttribute("cate2",vocationName2);
        //---------职业排行top10
        //---------HR人气top10
        List hr_name =new ArrayList();
        List count =new ArrayList();
        List<HRDate> HRDate= hrService.findHRDate();
        for (int i = 0; i <= HRDate.size()-1; i++){
            HRDate HRD= HRDate.get(i);
            hr_name.add(HRD.getHr_name());
            count.add(HRD.getDate());
        }
        model.addAttribute("nums3",count);
        model.addAttribute("cate3",hr_name);
        //---------HR人气top10
        //系统使用用户比例
        Integer inta=userService.countNumIsVIP();
        Integer intb=userService.countNumIsNotVIp();
        Integer inc=managerService.countAllManagerNum();
        model.addAttribute("countVipUser",inta);
        model.addAttribute("countNotVipUser",intb);
        model.addAttribute("countAllManagerNum",inc);
        //系统使用用户比例
        return "index_ht";
    }

    //管理员登录功能
    @PostMapping("/login")
    public String login_htPage(@RequestParam("manager_account") String manager_account,
                               @RequestParam("manager_password") String manager_password,
                               @RequestParam("power") Integer power,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session){
        String ip = request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        if(ip.equals("0:0:0:0:0:0:0:1")){
            ip="127.0.0.1";
        }
        System.out.println("IP地址："+ip);
        String agentStr = request.getHeader("user-agent");
        System.out.println(agentStr);
        UserAgent agent = UserAgent.parseUserAgentString(agentStr);
        //浏览器
        Browser browser = agent.getBrowser();
        System.out.println("类型："+browser.getBrowserType()+
                "\n名称："+browser.getName()+
                "\n厂商："+browser.getManufacturer()+
                "\n产品系列："+browser.getGroup()+
                "\n引擎："+browser.getRenderingEngine());

        //浏览器版本
        Version version = agent.getBrowserVersion();
        System.out.println("========================");
        System.out.println("主版本："+version.getMajorVersion()+
                "\n小版本："+version.getMinorVersion()+
                "\n完整版本："+version.getVersion());
        //操作系统
        System.out.println("========================");
        OperatingSystem os = agent.getOperatingSystem();
        System.out.println("名称："+os.getName()+
                "\n设备类型："+os.getDeviceType()+
                "\n产品系列："+os.getGroup()+
                "\n生成厂商："+os.getManufacturer());
        SysLog sysLog = new SysLog();
        Manager manager1 =  new Manager();
        manager1.setManager_account(manager_account);
        manager1.setManager_password(manager_password);
        manager1.setPower(power);
        Manager manager=managerService.ManagerLogin(manager1);
        if(manager!=null){
            sysLog.setUser_account(manager.getManager_account());
            sysLog.setIP_Address(ip);
            sysLog.setOS(os.getName());
            sysLog.setIE(browser.getName());
            sysLog.setLog_Content("系统登录");
            sysLog.setPower(power);
            Integer sys = sysLogService.InsertSysLog(sysLog);
            session.setAttribute("manager",manager);
            Integer countDealWith= subscribeInfoService.countDealWith();
            Integer countResume=resumeInfoService.countResume();
            Integer countAll =countDealWith+countResume;
            session.setAttribute("countDealWith",countDealWith);
            session.setAttribute("countResume",countResume);
            session.setAttribute("countAll",countAll);
            return "redirect:/ht/index";
        } else {
            return "redirect:/ht/login_ht";
        }
    }
    //登出
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        if (session.getAttribute("manager")==null){
            session.removeAttribute("manager");
        }
        return "redirect:/ht/login_ht";
    }
    //档案管理页面初始化
    @GetMapping("manage_file")
    public String manage_filePage(Model model1,
                                  HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        List<Manager> lists1 = managerService.managerFile();
        model1.addAttribute("managerinfo", lists1);
        List<Hr> lists2= hrService.findAllHR();
        model1.addAttribute("hrinfo", lists2);
        List<User> lists3= userService.findAllUser();
        model1.addAttribute("userinfo", lists3);
        String[] str=new String[]{"","普通用户","HR","普通管理员","高级管理员"};
        model1.addAttribute("str", str);
        Integer countDealWith= subscribeInfoService.countDealWith();
        Integer countResume=resumeInfoService.countResume();
        Integer countAll =countDealWith+countResume;
        session.setAttribute("countDealWith",countDealWith);
        session.setAttribute("countResume",countResume);
        session.setAttribute("countAll",countAll);
        return "manage_file";  //菜单导航（包括页面初始化）
    }

    //管理员信息的删
    @GetMapping("/delm/{mid}")
    public String delm(@PathVariable("mid")  Integer mid,HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        managerService.deleteManagerByID(mid);
        return "redirect:/ht/manage_file";
    }
    //进入添加管理员页面
    @GetMapping("/addm")
    public String addm(HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        return "manage_file_add";
    }
    //添加管理员
    @PostMapping("/addm")
    public String addm(Manager manager, @RequestParam("filepic") MultipartFile file,HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        //保存到文件到硬盘
        String fileName=file.getOriginalFilename();
        String filepath= FileUtil.getUpLoadFilePath();
        fileName =System.currentTimeMillis()+fileName;  //文件名称的处理（上传时间的毫秒+原文件名）
        try {
            FileUtil.uploadFile(file.getBytes(),filepath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //保存文件到数据库里
        manager.setManager_headpic(fileName);
        managerService.insertManager(manager);
        return "redirect:/ht/manage_file";
    }
    //进入更新管理员信息页面
    @GetMapping("/updatem/{mid}")
    public String updatem(@PathVariable("mid")  Integer mid,Model model,HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        Manager manager=managerService.findManagerByID(mid);
        model.addAttribute("manager",manager);
        return "manage_file_update";
    }
    //更新管理员信息
    @PostMapping("/updatem")
    public String updatemp(Manager manager, @RequestParam("filepic") MultipartFile file,HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        //保存到文件到硬盘
        String fileName=file.getOriginalFilename();
        String filepath= FileUtil.getUpLoadFilePath();
        fileName =System.currentTimeMillis()+fileName;  //文件名称的处理（上传时间的毫秒+原文件名）
        //输入格式：文件流、文件现在的真实路径、将保存的文件名
        try {
            FileUtil.uploadFile(file.getBytes(),filepath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //修改文件到数据库里
        manager.setManager_headpic(fileName);
        managerService.updateManager(manager);
        return "redirect:/ht/manage_file";
    }
    //管理员信息表的相关查询
    @PostMapping("/search_file_manager")
    public String search_ht_manager(@RequestParam(name="date_manager",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date date_manager,
                                    @RequestParam(name="account",required=false) String account,
                                    @RequestParam(name="search_name_manager",required=false) String search_name_manager,Model model,
                                    HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if(date_manager==null&&account.isEmpty()&&search_name_manager.isEmpty()){
            return "redirect:/ht/manage_file";
        }//按照日期查找管理员
        else if(date_manager!=null){
            java.sql.Date d= new java.sql.Date(date_manager.getTime());
            List<Manager> manager=managerService.findManagerByDate(d);
            model.addAttribute("managerinfo", manager);
            //查询条件的回显
            model.addAttribute("date_manager", d);
        }    //按照ID号查找管理员
        else if(!account.isEmpty()){
            List<Manager> lists1=managerService.findManagerByAccount(account);
            model.addAttribute("managerinfo", lists1);
            model.addAttribute("account", account);
        }
        else if(!search_name_manager.isEmpty()){
            List<Manager> lists1 =managerService.findManagerByName(search_name_manager);
            model.addAttribute("search_name_manager", search_name_manager);
        }else{
            return "redirect:/ht/manage_file";
        }
        List<Hr> lists2= hrService.findAllHR();
        model.addAttribute("hrinfo", lists2);
        List<User> lists3= userService.findAllUser();
        model.addAttribute("userinfo", lists3);
        String[] str=new String[]{"","普通用户","HR","普通管理员","高级管理员"};
        model.addAttribute("str", str);
        return "manage_file";
    }
    //查询HR的信息
    @GetMapping("/check_hr")
    public String checkhr(@RequestParam("hid") Integer hid , Model model,HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        Hr hr=hrService.findAHRByID(hid);
        model.addAttribute("hr", hr);
        return "checkhr";
    }
    //审核HR的信息
    @PostMapping("/check")
    public  String check(@RequestParam("hid") Integer hid ,
                         @RequestParam("isHr") Boolean isHr,
                         @RequestParam("message") String message,
                         HttpSession session){
        if (session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        Hr hr2=hrService.findAHRByID(hid);
        hr2.setIsHr(isHr);
        hr2.setMessage(message);
        hr2.setIsdealwith(true);
        hrService.update(hr2);
    return  "redirect:/ht/manage_file";
    }
}
