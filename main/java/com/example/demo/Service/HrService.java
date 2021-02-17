package com.example.demo.Service;

import com.example.demo.Entity.HRDate;
import com.example.demo.Entity.Hr;

import java.util.List;

public interface HrService {
    //hr注册    //查询是HR的所有信息
    //    List<Hr> findAllIsHR();
    Integer addHr(Hr hr);
    //检验hr是否存在
    List<Hr> HrAccountCheck(String hr_account);
    //hr登录
    Hr hrLogin(String hr_account,String hr_password);

    //查询HR所有信息
    List<Hr> findAllHR();

    //查询是HR的所有信息
    List<Hr> findAllIsHR();

    //按照账号模糊查找HR
    List<Hr> findHrByAccount(String hr_account);

    //按照真实姓名查找HR
    List<Hr> findHrByName(String hr_name);

    //按照真实姓名和账号模糊查找HR
    List<Hr> findHrByNameandAccount(String hr_name,String hr_account);

    //根据id查找HR
    Hr findAHRByID(Integer hid);

    Integer applyRe(Integer id);
    //更新信息
    Integer updateMessage(Hr hr);
    //更新HR信息
    Integer update(Hr hr);

    Integer countHr();

    List<HRDate>findHRDate();

    List<Hr> findAllHRAndPF();

    List<Hr> findHR();
}
