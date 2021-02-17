

package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Mapper.PingfenMapper;
import com.example.demo.Service.*;
import com.example.demo.Utils.FileUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("hr")
public class HrController {
    @Autowired
    private HrService hrService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResumeInfoService resumeInfoService;
    @Autowired
    private  SubscribeInfoService subscribeInfoService;
    @Autowired
    private PingfenMapper pingfenMapper;

    //hr注册
    @GetMapping("/addhr1")
    public String addHrPage(){
        return "registered_h";
    }
    //HR注册
    @PostMapping("/addhr")
    public String addHr(Hr hr, @RequestParam("filepic") MultipartFile file,@RequestParam("filepic1") MultipartFile file1) throws IOException {

        //保存到文件到硬盘
        String fileName=file.getOriginalFilename();
        String fileName1=file1.getOriginalFilename();
        String filepath= FileUtil.getUpLoadFilePath2();
        fileName =System.currentTimeMillis()+fileName;  //文件名称的处理（上传时间的毫秒+原文件名）
        try {
            FileUtil.uploadFile(file.getBytes(),filepath,fileName);
            FileUtil.uploadFile(file1.getBytes(),filepath,fileName1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //保存文件到数据库里
        hr.setHr_headpic1(fileName);
        hr.setHr_headpic2(fileName1);
        hrService.addHr(hr);
        return "redirect:/user/login1";
    }

    //HR员信息表的相关查询（后台）
    @PostMapping("/search_file_hr")
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
            List<Hr> lists1=hrService.findHrByAccount(account);
            model.addAttribute("hrinfo", lists1);
            model.addAttribute("account_hr", account);
        }   //按照真实姓名查找用户
        else if(name!=null){
            List<Hr> lists1 =hrService.findHrByName(name);
            model.addAttribute("hrinfo", lists1);
            model.addAttribute("name_hr", name);
        }else{
            List<Hr> lists1 =hrService.findHrByNameandAccount(name,account);
            model.addAttribute("hrinfo", lists1);
            model.addAttribute("account_hr", account);
            model.addAttribute("name_hr", name);
        }
        List<User> lists3= userService.findAllUser();
        model.addAttribute("userinfo", lists3);
        List<Manager> lists1 = managerService.managerFile();
        model.addAttribute("managerinfo", lists1);
        return "manage_file";
    }
    //简历上传
    @PostMapping("/resume_up")
    public String reupload(ResumeInfo resumeInfo,
                           @RequestParam("download") MultipartFile pic,
                           @RequestParam("file") MultipartFile file,
                           HttpSession session) throws IOException{
        if(session.getAttribute("hr")==null){
            return "redirect:/user/login1";
        }
        //get hr_account
        Hr hr = (Hr) session.getAttribute("hr");
        //获取文件原始名称
        String oldfileName=pic.getOriginalFilename();
        String oldfileName1=file.getOriginalFilename();
        //获取文件后缀
        String extension = "." + FilenameUtils.getExtension(pic.getOriginalFilename());
        String extension1 = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHss").format(new Date()) + UUID.randomUUID().toString().replace("-","")+extension;
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
        // *****临时文件*******
        //功能：图片复制到服务目录下,可以上传和文件之后可以正常显示
        String filepath= FileUtil.getUpLoadFilePath2();
        filepath=filepath+ "/" + dateFormat;
        File filepath2 = new File(filepath);
        if(!filepath2.exists()) dateDir.mkdirs();
        try{
            FileUtil.uploadFile(pic.getBytes(),filepath,newFileName);
            FileUtil.uploadFile(file.getBytes(),filepath,newFileName1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        // ************

        pic.transferTo(new File(dateDir,newFileName));
        file.transferTo(new File(dateDir,newFileName1));
        //保存文件到数据库里
        resumeInfo.setOldresumeName(oldfileName1);
        resumeInfo.setDownload_mini_address(newFileName);
        resumeInfo.setResume_download_address("/hr_file/" + dateFormat);
        resumeInfo.setResumeName(newFileName1);
        resumeInfo.setHid(hr.getHr_account());
        resumeInfoService.hruploadRe(resumeInfo);
        return "redirect:/user/hr_index";
    }

    //简历下载
    @GetMapping("/download")
    public void hrDown(String openStyle,Integer id, HttpServletResponse response) throws IOException {
        //打开方式
        openStyle = openStyle==null?"attachment":openStyle;
        //获取文件信息
        ResumeInfo resumeInfo = resumeInfoService.findAllResumeInfoByID(id);
        //点击下载链接才更新下载次数
        if("attachment".equals(openStyle)){
            resumeInfo.setResume_times(resumeInfo.getResume_times()+1);
            resumeInfoService.updateTimes(resumeInfo);
        }
        //获取文件名字与文件路径获取文件输入流
        String c = System.getProperty("user.dir");
        String realPath = ResourceUtils.getURL(c+"\\src\\main\\resources").getPath()+"/static" + resumeInfo.getResume_download_address();
        //附件下载
        response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(resumeInfo.getOldresumeName(),"UTF-8"));
        //获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPath,resumeInfo.getResumeName()));
        //响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    //简历删除
    @GetMapping("/delete")
    public String deleteResume(Integer rid){
        //删除数据库中的文件
        resumeInfoService.XJResumeByID(rid);
        return "redirect:/user/hr_index";
    }
    //简历更新
    @GetMapping("findAllJSON")
    @ResponseBody
    public List<ResumeInfo> findAllJSON(HttpSession session){
        //根据id查询文件
        Hr hr = (Hr) session.getAttribute("hr");
        List<ResumeInfo> ResumeInfo=resumeInfoService.findAllResumeInfoByAccount(hr.getHr_account());
        return ResumeInfo;
    }
    //HR进入优化页面
    @GetMapping("youhua")
    public String youhua(Model model,HttpSession session){
        if(session.getAttribute("hr")!=null){
        Hr hr = (Hr) session.getAttribute("hr");
        List<SubscribeInfo> list= subscribeInfoService.findAllByHRID(hr.getHid());
        model.addAttribute("subscribeInfo",list);
            String str[]={"","接受预约","正在处理","已处理"};
            model.addAttribute("subscribeInfo",list);
            model.addAttribute("str",str);
        }else{
            return "redirect:/user/login1";
        }
        return "hr_youhua";
    }
    //简历下载----有用户上传之后发送给HR的简历
    //上传直接保存下载地址，HR根据文件的下载地址直接下载
    @GetMapping("/download_hr")
    public void Down(@RequestParam(name="subscribe_id") Integer subscribe_id,
                     String openStyle,String resume_download_address,
                     HttpServletResponse response) throws IOException {
        SubscribeInfo subscribeInfo= subscribeInfoService.findAllByID(subscribe_id);
        String oldName=subscribeInfo.getOldName();
        //打开方式
        openStyle = openStyle==null?"attachment":openStyle;
        //获取文件名字与文件路径获取文件输入流
        String c = System.getProperty("user.dir");
        String realPath = ResourceUtils.getURL(c+"\\src\\main\\resources").getPath()+"/static/" + resume_download_address;
        //附件下载
        String OldresumeName ="";
        String str[]=resume_download_address.split("/");//字符切片
        str[3]=OldresumeName;
        response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(oldName,"UTF-8"));
        //获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPath,OldresumeName));//新文件名就是保存地址名
        //响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }
    //修改简历的下载
    @GetMapping("/downloadNewRes")
    public void Down(Integer id,
                     HttpServletResponse response) throws IOException {
        SubscribeInfo subscribeInfo= subscribeInfoService.findAllByID(id);
        String resume_download_address = subscribeInfo.getResume_download_address();
        String oldName=subscribeInfo.getOldName();
        //获取文件名字与文件路径获取文件输入流
        String c = System.getProperty("user.dir");
        String realPath = ResourceUtils.getURL(c+"\\src\\main\\resources").getPath()+"/static/" + resume_download_address;
        //附件下载
        String OldresumeName ="";
        String str[]=resume_download_address.split("/");//字符切片
        str[3]=OldresumeName;
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(oldName,"UTF-8"));
        //获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPath,OldresumeName));//新文件名就是保存地址名
        //响应输出流
        ServletOutputStream os = response.getOutputStream();
        //文件拷贝
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

    @GetMapping("/todealwith")
    public String todealwith(@RequestParam(name="subscribe_id") Integer subscribe_id,
                             @RequestParam(name="isdealwith") Integer isdealwith,
                             HttpSession session){
        if(session.getAttribute("hr")==null){
            return "redirect:/user/login1";
        }
        if(isdealwith==1){
            Integer i=2;
            subscribeInfoService.updateisdealwith(subscribe_id,i);
        }else{
            return "redirect:/hr/youhua";
        }
        return "redirect:/hr/youhua";
    }

    //HR查看用户基本信息
    @GetMapping("/userdetail/{user_id}")
    public String userDetail(@PathVariable("user_id")  Integer user_id,
                             Model model,
                             HttpSession session){
        if(session.getAttribute("hr")==null){
            return "redirect:/user/login1";
        }
        User user= userService.findUserByID(user_id);
        model.addAttribute("user",user);
        return "userDetail";
    }
    //申请发布简历
    @GetMapping("/apply")
    public String applyRe(Integer rid,
                          HttpSession session){
        if(session.getAttribute("hr")==null){
            return "redirect:/ht/login_ht";
        }
        hrService.applyRe(rid);
        resumeInfoService.updateState(rid);
        return "redirect:/user/hr_index";
    }

    @PostMapping("/uploadNewRe")
    public String uploadNewRe(@RequestParam(name="subscribe_id") Integer subscribe_id,
                              @RequestParam("file") MultipartFile file,
                              HttpSession session) throws IOException {
        if(session.getAttribute("hr")==null){
            return "redirect:/user/login1";
        }
        SubscribeInfo subscribeInfo=subscribeInfoService.findAllByID(subscribe_id);
        String oldfileName=file.getOriginalFilename();
        //获取文件后缀
        String extension1 = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名称
        String newFileName1 = new SimpleDateFormat("yyyyMMddHHss").format(new Date()) + UUID.randomUUID().toString().replace("-","")+extension1;
        //处理根据日期生成目录
        //获取本地路径
        String c = System.getProperty("user.dir");
        String realPath = ResourceUtils.getURL(c+"\\src\\main\\resources").getPath()+"/static/hr_file";
        Date date = subscribeInfo.getSubscribe_date();
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String dateDirPath = realPath + "/" + dateFormat;
        File dateDir = new File(dateDirPath);
        if(!dateDir.exists()) dateDir.mkdirs();
        //处理文件上传
        file.transferTo(new File(dateDir,newFileName1));
        //保存文件到数据库里
        subscribeInfo.setResume_download_address("/hr_file/" + dateFormat+"/"+newFileName1);
        subscribeInfo.setOldName(oldfileName);
        subscribeInfoService.updateResum(subscribeInfo);
        return "redirect:/hr/youhua";
    }
    @GetMapping("hrDetail")
    public String getHrDetail(Model model,HttpSession session){
        if(session.getAttribute("hr")==null){
            return "redirect:/user/login1";
        }
        Hr hr = (Hr) session.getAttribute("hr");
        Hr hr1 = hrService.findAHRByID(hr.getHid());
        model.addAttribute("hr",hr1);
        return "person_index_hr";
    }
    //用户查看HR的信息
    @GetMapping("hrView")
    public String getHrDetail(Model model,
                              Integer hid,
                              Integer isdealwith,
                              HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";
        }
        Hr hr1 = hrService.findAHRByID(hid);
        model.addAttribute("hr",hr1);
        model.addAttribute("isdealwith",isdealwith);
        return "hrDetail";
    }

    //hr更新个人信息
    @PostMapping("/updateHrMessage")
    public String updateMessage(Hr hr1, @RequestParam("filepic") MultipartFile file,HttpSession session){
        if(session.getAttribute("hr")==null){
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
        Hr hr = (Hr)session.getAttribute("hr");
        hr.setHr_name(hr1.getHr_name());
        if(fileName!=null){
            hr.setHr_headpic(fileName);}
        hr.setHr_tel(hr1.getHr_tel());
        hr.setHr_sex(hr1.getHr_sex());
        hr.setHr_jl(hr1.getHr_jl());
        hr.setHr_qq(hr1.getHr_qq());
        hr.setHr_password(hr1.getHr_password());
        hrService.updateMessage(hr);
        return "redirect:/hr/hrDetail";
    }
    //用户对Hr进行评分
    @RequestMapping("/hrPinFen")
    public String hrPinFen(@RequestParam("hr_id") Integer hr_id,
                           Model model,
                           HttpSession session){
        if(session.getAttribute("user")==null){
            return "redirect:/user/login1";}
        Hr hr= hrService.findAHRByID(hr_id);
        model.addAttribute("hr",hr);
        return "pingfenHr";
    }
    @RequestMapping("/hrPinFen2")
    public String hrPinFen(@RequestParam("starNum") Integer fenshu,
                           @RequestParam("pingfenremark") String pingfenremark,
                           @RequestParam("hid") Integer hid){
        Pingfen pingfen = new Pingfen();
        pingfen.setHid(hid);
        pingfen.setHrmark(fenshu);
        pingfen.setHrremark(pingfenremark);
        pingfenMapper.pingfenHr(pingfen);
        return "redirect:/user/person_reserve";
    }
}