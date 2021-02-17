package com.example.demo.Serviceimpl;

import com.example.demo.Entity.LiuY;
import com.example.demo.Entity.SysLog;
import com.example.demo.Entity.User;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User UserLogin(String user_account, String user_password) {
        return userMapper.UserLogin(user_account,user_password);
    }

    @Override
    public Integer UserRegi(User user) {
        return userMapper.UserRegi(user);
    }

    @Override
    public List<User> UserAccountCheck(String user_account) {
        return userMapper.UserAccountCheck(user_account);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public List<User> findUserByAccount(String user_account) {
        return userMapper.findUserByAccount(user_account);
    }

    @Override
    public List<User> findUserByName(String user_name) {
        return userMapper.findUserByName(user_name);
    }

    @Override
    public List<User> findUserByNameandAccount(String user_name, String user_account) {
        return userMapper.findUserByNameandAccount(user_name,user_account);
    }

    @Override
    public PageInfo<LiuY> findLiuy(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<LiuY> list = userMapper.findLiuy();
        PageInfo<LiuY> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public User findUserByID(Integer uid) {
        return userMapper.findUserByID(uid);
    }

    @Override
    public Integer upgrade(Integer uid) {
        return userMapper.upgrade(uid);
    }

    @Override
    public Integer updateUser(User user) { return userMapper.updateUser(user); }

    @Override
    public Integer updateUserByID(Integer uid) {
        return userMapper.updateUserByID(uid);
    }

    @Override
    public Integer count() {
        return userMapper.countNum();
    }

    @Override
    public Integer countNumIsVIP() {
        return userMapper.countNumIsVIP();
    }

    @Override
    public Integer countNumIsNotVIp() {
        return userMapper.countNumIsNotVIp();
    }

    @Override
    public Integer addLiuy(LiuY liuY) {
        return userMapper.addLiuy(liuY);
    }

}
