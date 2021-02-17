package com.example.demo.Service;

import com.example.demo.Entity.Manager;

import java.sql.Date;
import java.util.List;

public interface ManagerService {
    //登录
    Manager ManagerLogin(Manager manager);
    //后台档案管理(查找所有管理员信息)
    List<Manager> managerFile();
    //根据管理员ID号删除管理员
    Integer deleteManagerByID(Integer mid);
    //添加管理者
    Integer insertManager(Manager manager);
    //按照ID号查找管理员
    Manager findManagerByID(Integer mid);
    //根据账号查找管理员
    List<Manager> findManagerByAccount(String manager_account);
    //按照日期查找管理员
    List<Manager> findManagerByDate(Date manager_date);
    //按照真实姓名查找管理员
    List<Manager> findManagerByName(String manager_name);
    //更新管理员信息
    Integer updateManager(Manager manager);
    //查询管理员人数
    Integer countAllManagerNum();

}
