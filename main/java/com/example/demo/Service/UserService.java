package com.example.demo.Service;

import com.example.demo.Entity.LiuY;
import com.example.demo.Entity.SysLog;
import com.example.demo.Entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //用户登录
    User UserLogin(String user_account, String user_password);
    //用户注册
    Integer UserRegi(User user);
    //检验用户名是否存在
    List<User> UserAccountCheck(String user_account);
    //查找所有用户信息
    List<User> findAllUser();
    //按照账号模糊查找用户
    List<User> findUserByAccount(String user_account);
    //按照真实姓名查找用户
    List<User> findUserByName(String user_name);
    //按照真实姓名和账号模糊查找用户
    List<User> findUserByNameandAccount(String user_name,String user_account);
    //根据ID号查找用户信息
    User findUserByID(Integer uid);
    //将用户变为VIP
    Integer upgrade(Integer uid);
    //修改个人信息
    Integer updateUser(User user);
    //修改个人信息
    Integer updateUserByID(Integer uid);
    Integer count();
    Integer countNumIsVIP();
    Integer countNumIsNotVIp();
    //留言处理
    Integer addLiuy(LiuY liuY);
    PageInfo<LiuY> findLiuy(Integer pageIndex, Integer pageSize);

}
