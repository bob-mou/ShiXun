package com.example.demo.Service;

import com.example.demo.Entity.SysLog;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

public interface SysLogService {
    Integer InsertSysLog(SysLog sysLog);

    //查询全部日志
    PageInfo<SysLog> findAllSysLog(Integer pageIndex, Integer pageSize);

    //按照账号查找登录日志
    PageInfo<SysLog> findSysLogByAccount(Integer pageIndex, Integer pageSize,String account);

    //按照日期查找登录日志
    PageInfo<SysLog> findSysLogByDate(Integer pageIndex, Integer pageSize,Date date);

    //按照账号和日期查找登录日志
    PageInfo<SysLog> findSysLogByDateandAccount(Integer pageIndex, Integer pageSize,Date date,String account);

    //按照权限查找登录日志
    PageInfo<SysLog> findSysLogByPower(Integer pageIndex, Integer pageSize,Integer power);
}
