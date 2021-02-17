package com.example.demo.Mapper;

import com.example.demo.Entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;


@Mapper
public interface SysLogMapper {
    @Insert("insert into sys_log(user_account,power,Log_Content,IP_Address,OS,IE,CreateDate,Remark) \n" +
            "values(#{user_account},#{power},#{Log_Content},#{IP_Address},#{OS},#{IE},sysdate(),'无')")
    Integer InsertSysLog(SysLog sysLog);
    //(查找所有登录信息)
    @Select("SELECT * FROM sys_log order by CreateDate desc")
    List<SysLog> findAllSysLog();

    //按照账号查找登录日志
    @Select("SELECT * FROM sys_log where user_account like '%${account}%' order by CreateDate desc")
    List<SysLog> findSysLogByAccount(String account);

    //按照日期查找登录日志
    @Select(" select * from sys_log where CreateDate>=#{date} order by CreateDate ")
    List<SysLog> findSysLogByDate(Date date);

    //按照账号和日期查找登录日志
    @Select("SELECT * FROM sys_log where user_account like '%${account}%' AND CreateDate>=#{date} order by CreateDate desc")
    List<SysLog> findSysLogByDateandAccount(Date date,String account);

    //按照权限查找登录日志
    @Select("SELECT * FROM sys_log where power=#{power} order by CreateDate desc")
    List<SysLog> findSysLogByPower(Integer power);
}
