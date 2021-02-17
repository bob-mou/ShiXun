
package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Service.*;
import com.example.demo.Utils.FileUtil;
import com.github.pagehelper.PageInfo;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;



@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private HrService hrService;
    @Autowired
    private VocationService vocationService;
    @Autowired
    private ResumeInfoService resumeInfoService;
    @Autowired
    private PinfenService pinfenService;
    @Autowired
    private SubscribeInfoService subscribeInfoService;
    @GetMapping("/all")
    public String indexPage() {
        return "index";  //index.html
    }
    //进入HR主页面
    @GetMapping("/hr_index")
    public String gethr_indexPage(Model model,HttpSession session) {
        if((Hr) session.getAttribute("hr")!=null){
            Hr hr = (Hr) session.getAttribute("hr");
            List<ResumeInfo> lists =resumeInfoService.findAllResumeInfoByAccount(hr.getHr_account());
            model.addAttribute("resumeinfo",lists);
        }else{
            return "redirect:/user/login1";
        }
        return "hr_index";
    }
    @GetMapping("/person_index")
    public String getperson_indexPage(Model model,HttpSession session){
        if(session.getAttribute("user")!=null){
            User user= (User) session.getAttribute("user");
            model.addAttribute("user", user);
        }else{
            return "redirect:/user/login1";
        }
        return "person_user";
    }
    //HR进入上传简历模板页面
    @GetMapping("/hr_load")
    public String gethr_loadPage(Model model,HttpSession session) {
        if(session.getAttribute("hr")==null){
            return "redirect:/user/login1";}
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        return "hr_load";
    }
    @GetMapping("login1")
    public String getLoginPage(){
        return "login";
    }
    //用户进入商务模板
    @GetMapping("busy")
    public String getBusyPage(Model model){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfo();
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }
    @GetMapping("/select_hr")
    public String select_hrPage(Model model) {
        List<Hr> list=hrService.findHR();
        model.addAttribute("hrinfo", list);
        return "select_hr";
    }
    //用户选择HR后进入页面显示HR信息
    @GetMapping("/youhua/{hid}")
    public String youhua_hr(@PathVariable("hid")  Integer hid,Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "redirect:/user/login1";
        }
        if(user!=null&&!user.getVip()){
            return "qd_vip";
        }
        if (user!=null&&user.getVip()) {
            Hr hr = hrService.findAHRByID(hid);
            Integer avgHR=pinfenService.avgHR(hid);
            model.addAttribute("hr",hr);
            model.addAttribute("avgHR", avgHR);
            List<VocationInfo> lists = vocationService.findAllVoc();
            model.addAttribute("vocationinfo", lists);
        }
        return "youhua";
    }
    //用户优化预约处理
    @GetMapping("/person_reserve")
    public String youhua_user(Model model,HttpSession session) {
        if ((User) session.getAttribute("user")!=null) {
            User user= (User) session.getAttribute("user");
            Integer uid= user.getUid();
            List<SubscribeInfo> lists = subscribeInfoService.findAllByUserID(uid);
            model.addAttribute("subscribeInfo", lists);
            String str[]={"","待处理","正在处理","已处理"};
            model.addAttribute("str",str);
        }else {
            return "redirect:/user/login1";
        }
        return "person_reserve";
    }
    //文件上传保存文件下载地址，根据文件地址下载
    @PostMapping("/youhua")
    public String you(SubscribeInfo subscribeInfo,@RequestParam("file") MultipartFile file,
                      HttpSession session) throws IOException {
        if(subscribeInfo!=null){
            if((User)session.getAttribute("user")!=null){
                //获取用户账号
                User user = (User)session.getAttribute("user");
                //获取文件原始名称
                String oldfileName=file.getOriginalFilename();
                //获取文件后缀
                String extension1 = "." + FilenameUtils.getExtension(file.getOriginalFilename());
                //生成新的文件名称
                String newFileName1 = new SimpleDateFormat("yyyyMMddHHss").format(new Date()) + UUID.randomUUID().toString().replace("-","")+extension1;
                //处理根据日期生成目录
                //获取本地路径
                String c = System.getProperty("user.dir");
                String realPath = ResourceUtils.getURL(c+"\\src\\main\\resources").getPath()+"/static/hr_file";
                String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String dateDirPath = realPath + "/" + dateFormat;
                File dateDir = new File(dateDirPath);
                if(!dateDir.exists()) dateDir.mkdirs();
                    //处理文件上传
                    file.transferTo(new File(dateDir,newFileName1));
                    //保存文件到数据库里
                    subscribeInfo.setResume_download_address("/hr_file/" + dateFormat+"/"+newFileName1);
                    Date date=new Date();
                    java.sql.Date d= new java.sql.Date(date.getTime());
                    subscribeInfo.setOldName(oldfileName);
                    subscribeInfo.setSubscribe_date(d);
                    subscribeInfo.setUser_id(user.getUid());
                    subscribeInfoService.inster(subscribeInfo);
                }else{
                    return "redirect:/user/login1";
            }
        }
        return "index";
    }
    //用户查询信息
    @PostMapping("/husysearch")
    public String husysearch(Integer vocation_id,Model model,HttpSession session){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfoByID(vocation_id);
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }
    @GetMapping("/download")
    public String downloadPage(Integer id,Model model){
        ResumeInfo resumeinfo =resumeInfoService.findAllResumeInfoByID(id);
        model.addAttribute("ResumeInfo",resumeinfo);
        return "downloadResume";
    }

    @GetMapping("registered")
    public String getRegisterPage(){
        return "registered";
    }

    @GetMapping("registered_h")
    public String getRegisterPage_h(){
        return "registered_h";
    }

    @GetMapping("registered_f")
    public String getRegidter_fPage(){
        return "registered_f";
    }

    @GetMapping("/optimize")
    public String getoptimizePage() {
        return "optimize";
    }

    @GetMapping("/forget")
    public String getforgetPage() {
        return "forget";
    }

    @GetMapping("/qd_vip")
    public String getqd_vipPage() {
        return "qd_vip";
    }

    //系统登录
    @PostMapping("login")
    public String userLogin(@RequestParam("account") String account,
                            @RequestParam("password") String password,
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
        User user = null;
        Hr hr = null;
        if(power==1){
            user=userService.UserLogin(account,password);
        }
        if(power==2){
            hr = hrService.hrLogin(account,password);
        }
        if(user!=null){
            sysLog.setUser_account(user.getUser_account());
            sysLog.setIP_Address(ip);
            sysLog.setOS(os.getName());
            sysLog.setIE(browser.getName());
            sysLog.setLog_Content("系统登录");
            sysLog.setPower(power);
            Integer sys = sysLogService.InsertSysLog(sysLog);
            Integer uid=user.getUid();
            session.setAttribute("uid",uid);
            session.setAttribute("user",user);
            return "redirect:/user/all";
        }
        if(hr!=null){
            sysLog.setUser_account(hr.getHr_account());
            sysLog.setIP_Address(ip);
            sysLog.setOS(os.getName());
            sysLog.setIE(browser.getName());
            sysLog.setLog_Content("系统登录");
            sysLog.setPower(power);
            Integer sys = sysLogService.InsertSysLog(sysLog);
            session.setAttribute("hr",hr);
            return "redirect:/user/hr_index";
        }
        else {
            return "redirect:/user/login1";
        }
    }
    //用户注册
    @PostMapping("/regi")
    public String UserRegi(User user){
        List<User> user1 = userService.UserAccountCheck(user.getUser_account());
        if(user1.isEmpty()){
            Date date=new Date();
            java.sql.Date d= new java.sql.Date(date.getTime());
            user.setUser_date(d);
            user.setUser_headpic("admin.png");
            Integer i = userService.UserRegi(user);
            return "redirect:/user/login1";
        }else{
            return "redirect:/user/registered_f";
        }
    }
    //Hr注册
    @PostMapping("/addhr")
    public String addHr(Hr hr){
        List<Hr> hr1 = hrService.HrAccountCheck(hr.getHr_account());
        if(hr1.isEmpty()){
            Integer i = hrService.addHr(hr);
            return "redirect:/user/login1";
        }else{
            return "redirect:/user/registered_h";
        }
    }
    //用户信息表的相关查询（后台）
    @PostMapping("/search_file_user")
    public String search_ht_manager(@RequestParam(name="account",required=false) String account,
                                    @RequestParam(name="name",required=false) String name,
                                    Model model,
                                    HttpSession session){
        if(session.getAttribute("manager")==null){
            return "redirect:/ht/login_ht";
        }
        if(account==""&&name==null){
            return "redirect:/ht/manage_file";
        }    //按照账号查找用户
        else if(account!=""){
            List<User> lists1=userService.findUserByAccount(account);
            model.addAttribute("userinfo", lists1);
            model.addAttribute("account_user", account);
        }   //按照真实姓名查找用户
        else if(name!=null){
            List<User> lists1 =userService.findUserByName(name);
            model.addAttribute("userinfo", lists1);
            model.addAttribute("name_user", name);
        }else{
            List<User> lists1 =userService.findUserByNameandAccount(name,account);
            model.addAttribute("userinfo", lists1);
            model.addAttribute("account_user", account);
            model.addAttribute("name_user", name);
        }
        List<Hr> lists2= hrService.findAllHR();
        model.addAttribute("hrinfo", lists2);
        List<Manager> lists1 = managerService.managerFile();
        model.addAttribute("managerinfo", lists1);
        return "manage_file";
    }
    //进入菜单栏
    @GetMapping("/menu")
    public String testPage() {
        return "liuy";
    }
    //简历更新
    @GetMapping("findAllJSON")
    @ResponseBody
    public List<ResumeInfo> findAllJSON(){
        List<ResumeInfo> ResumeInfo=resumeInfoService.findAllResumeInfo();
        return ResumeInfo;
    }
    //简历评分
    @PostMapping("/pingfenRe")
    public String pingfenRe(@RequestParam("starNum") Integer fenshu,
                            @RequestParam("pingfenremark") String pingfenremark,
                            @RequestParam("rid") Integer rid,
                            Model model){
        Pingfen pingfen = new Pingfen();
        pingfen.setPingfenmark(fenshu);
        pingfen.setPingfenremark(pingfenremark);
        pingfen.setRid(rid);
        ResumeInfo resumeinfo =resumeInfoService.findAllResumeInfoByID(rid);
        model.addAttribute("ResumeInfo",resumeinfo);
        pinfenService.pingfenRe(pingfen);
        return "downloadResume_s";
    }
    //根据姓名查询hr
    @RequestMapping("hrsearch")
    public String hrSerchByHrName(@RequestParam("hr_name") String hr_name,Model model){
        List<Hr> list = hrService.findHrByName(hr_name);
        model.addAttribute("hrinfo", list);
        return "select_hr";
    }
    //更新个人信息(用户的)
    @PostMapping("updateMessage")
    public String updateMessage(User user, @RequestParam("user_pic") MultipartFile file,HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";}
        //保存到文件到硬盘
        String fileName=file.getOriginalFilename();
        String filepath= FileUtil.getUpLoadFilePath();
        fileName =System.currentTimeMillis()+fileName;  //文件名称的处理（上传时间的毫秒+原文件名）
        try {
            FileUtil.uploadFile(file.getBytes(),filepath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //修改到数据库里
        User user2 = (User)session.getAttribute("user");
        user2.setUser_name(user.getUser_name());
        user2.setUser_headpic(fileName);
        user2.setUser_tel(user.getUser_tel());
        user2.setUser_sex(user.getUser_sex());
        userService.updateUser(user2);
        return "redirect:/user/person_index";
    }

    //最新
    @RequestMapping("/findReByDate")
    public String findReByDate(Model model){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfoByDate();
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }
    //人气
    @RequestMapping("/findReByTimes")
    public String ffindReByTimes(Model model){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfoByTimes();
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }
    //好评
    @RequestMapping("/findReByFenshu")
    public String ffindReByFenshu(Model model){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfoByFenshu();
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }

    //按照名字查询
    @GetMapping("/findReByID")
    public String findReByName(Integer id,Model model){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfoByID(id);
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }

    //按照名字查询
    @PostMapping("/findReByName")
    public String findReByName(@RequestParam("reName") String reName,Model model){
        List<VocationInfo> lists = vocationService.findAllVoc();
        model.addAttribute("vocationinfo", lists);
        List<ResumeInfo> list= resumeInfoService.findResumeInfoByName(reName);
        model.addAttribute("resumeinfo",list);
        return "businessM";
    }
    @PostMapping("/userdealwith")
    public String userdealwith(@RequestParam(name="subscribe_id") Integer subscribe_id,
                             @RequestParam(name="isdealwith") Integer isdealwith,
                             HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";
        }
        if(isdealwith==2){
            Integer j=3;
            subscribeInfoService.updateisdealwith(subscribe_id,j);}
        return "redirect:/user/person_reserve";
    }
    //留言
    @PostMapping("/liuy2")
    public String addLiuy(LiuY liuY){
        if(liuY.getName()==null){
            liuY.setName("游客");
        }
        userService.addLiuy(liuY);
        return "redirect:/user/liuy";
    }
    @GetMapping("/liuy")
    public String getpage(Model model,
                          @RequestParam(value="pageIndex",defaultValue = "1") Integer pageIndex,
                          @RequestParam(value="pageSize",defaultValue = "11") Integer pageSize){
        PageInfo<LiuY> liuY=userService.findLiuy(pageIndex,pageSize);
        model.addAttribute("liuY",liuY);
        return "liuy";
    }
}