package com.example.demo.Serviceimpl;

import com.example.demo.Entity.SysLog;
import com.example.demo.Mapper.SysLogMapper;
import com.example.demo.Service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public Integer InsertSysLog(SysLog sysLog) {
        return sysLogMapper.InsertSysLog(sysLog);
    }

    //查询所有日志
    @Override
    public PageInfo<SysLog> findAllSysLog(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SysLog> list = sysLogMapper.findAllSysLog();
        PageInfo<SysLog> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public PageInfo<SysLog> findSysLogByAccount(Integer pageIndex,Integer pageSize,String account) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SysLog> list = sysLogMapper.findSysLogByAccount(account);
        PageInfo<SysLog> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public PageInfo<SysLog> findSysLogByDate(Integer pageIndex,Integer pageSize,Date date) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SysLog> list = sysLogMapper.findSysLogByDate(date);
        PageInfo<SysLog> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public PageInfo<SysLog> findSysLogByDateandAccount(Integer pageIndex,Integer pageSize,Date date,String account){
        PageHelper.startPage(pageIndex,pageSize);
        List<SysLog> list = sysLogMapper.findSysLogByDateandAccount(date,account);
        PageInfo<SysLog> info = new PageInfo<>(list);
        return info;
    }
    @Override
    public PageInfo<SysLog> findSysLogByPower(Integer pageIndex,Integer pageSize,Integer power) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SysLog> list = sysLogMapper.findSysLogByPower(power);
        PageInfo<SysLog> info = new PageInfo<>(list);
        return info;
    }


}
