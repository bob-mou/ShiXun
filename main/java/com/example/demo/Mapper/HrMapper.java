package com.example.demo.Mapper;

import com.example.demo.Entity.HRDate;
import com.example.demo.Entity.Hr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.util.List;

@Mapper
public interface HrMapper {

    //HR注册
    @Insert("insert into hr_info(register_date,hr_account,hr_name,hr_password,hr_sex,hr_tel,hr_qq,hr_headpic1,hr_headpic2,hr_jl)\n" +
            "values(sysdate(),#{hr_account},#{hr_name},#{hr_password},#{hr_sex},#{hr_tel},#{hr_qq},#{hr_headpic1},#{hr_headpic2},#{hr_jl})")
    Integer addHr(Hr hr);

    //查询HR的信息
    @Select("SELECT * FROM hr_info where hr_account=#{hr_account} order by register_date desc")
    List<Hr> HrAccountCheck(String hr_account);

    //HR登录
    @Select("SELECT * FROM hr_info where hr_account=#{hr_account} and hr_password=#{hr_password} and isHr='1' ")
    Hr hrLogin(String hr_account,String hr_password);

    //查询HR所有信息
    @Select("select * from hr_info order by register_date desc")
    List<Hr> findAllHR();

    //后台用的查询全部的HR的信息并且按照日期倒叙排列
    @Select("select * from hr_info where isHr=true order by register_date desc")
    List<Hr> findAllIsHR();

    //按照账号模糊查找HR
    @Select("SELECT * FROM hr_info where hr_account like '%${hr_account}%' order by register_date desc")
    List<Hr> findHrByAccount(String hr_account);

    //按照真实姓名查找HR
    @Select("SELECT * FROM hr_info where hr_name like '%${hr_name}%' and isHr=true ")
    List<Hr> findHrByName(String hr_name);

    //按照真实姓名查找HR
    @Select("SELECT * FROM hr_info where hr_name like '%${hr_name}%'  by register_date desc")
    List<Hr> findHrByName2(String hr_name);

    //按照真实姓名和账号模糊查找HR
    @Select("SELECT * FROM hr_info where hr_name like '%${hr_name}%' and hr_account like '%${hr_account}%' order by register_date desc")
    List<Hr> findHrByNameandAccount(String hr_name,String hr_account);

    @Select("select * from hr_info where hid=#{hid}")
    Hr findAHRByID(Integer hid);

    @Insert("insert into apply_for_publishlist(resume_id,isPublished,resume_apply_date)values(#{id},0,sysdate()) ")
    Integer applyRe(Integer id);

    @Update("UPDATE hr_info SET isHr=#{isHr}, register_date=#{register_date},hr_account=#{hr_account},hr_name=#{hr_name},hr_password=#{hr_password},hr_sex=#{hr_sex},hr_tel=#{hr_tel},hr_qq=#{hr_qq},hr_headpic1=#{hr_headpic1},hr_headpic2=#{hr_headpic2},hr_headpic=#{hr_headpic},hr_jl=#{hr_jl},message=#{message},isdealwith=#{isdealwith} WHERE hid=#{hid}")
    Integer update(Hr hr);

    @Update("update hr_info set hr_name=#{hr_name},hr_sex=#{hr_sex},hr_tel=#{hr_tel},hr_headpic=#{hr_headpic},hr_jl=#{hr_jl},message=#{message},hr_password=#{hr_password},hr_qq=#{hr_qq},hr_account=#{hr_account} where hid=#{hid}")
    Integer updateMessage(Hr hr);

    @Select("select count(*) from hr_info")
    Integer countHr();

    @Select("SELECT hr_info.hr_name ,count(subscribeinfo.hr_id) as date FROM subscribeinfo join hr_info on hr_info.hid= subscribeinfo.hr_id group by subscribeinfo.hr_id order by count(*) desc limit 0,10;")
    List<HRDate>findHRDate();

    //前台使用的查询，查询HR的所有信息和评分（HR推荐页面使用）
    @Select("select hr_info.*,avgHR from hr_info join ((select avg(pingfen.hrmark) as avgHR,hid from pingfen group by hid) as newt ) on newt.hid=hr_info.hid  where hr_info.isHr=true;")
    List<Hr> findAllHRAndPF();

    //查询审批过后的HR的信息（HR推荐页面会用到）
    @Select("select hr_info.*from hr_info  where hr_info.isHr=true")
    List<Hr> findHR();

}