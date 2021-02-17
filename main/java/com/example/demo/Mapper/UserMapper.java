package com.example.demo.Mapper;

import com.example.demo.Entity.LiuY;
import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    //登录
    @Select("select * from user_info where user_account=#{user_account} and user_password=#{user_password}")
    User UserLogin(String user_account, String user_password);

    //根据ID号查找用户信息
    @Select("select * from user_info where uid=#{uid} ")
    User findUserByID(Integer uid);

    //注册
    @Insert("insert into user_info(user_name,user_account,user_password,user_date,user_sex,user_tel,user_headpic) \n" +
            "           values(#{user_name},#{user_account},#{user_password},#{user_date},#{user_sex},#{user_tel},#{user_headpic})")
    Integer UserRegi(User user);

    //检验用户名是否存在
    @Select("select * from user_info where user_account=#{user_account}")
    List<User> UserAccountCheck(String user_account);

    //后台档案管理(查找所有用户信息)
    @Select("select * from user_info")
    List<User> findAllUser();

    //按照账号模糊查找用户
    @Select("SELECT * FROM user_info where user_account like '%${user_account}%'")
    List<User> findUserByAccount(String user_account);

    //按照真实姓名查找用户
    @Select("SELECT * FROM user_info where user_name like '%${user_name%}'")
    List<User> findUserByName(String user_name);

    //按照真实姓名和账号模糊查找用户
    @Select("SELECT * FROM user_info where user_name like '%${user_name}%' AND user_account like '%${user_account}%'")
    List<User> findUserByNameandAccount(String user_name,String user_account);

    //修改个人信息
    @Update("update user_info set user_name=#{user_name},user_sex=#{user_sex},user_tel=#{user_tel},user_headpic=#{user_headpic} where uid=#{uid}")
    Integer updateUser(User user);

    //修改个人信息
    @Update("update user_info set user_name=#{user_name},user_sex=#{user_sex},user_tel=#{user_tel},user_headpic=#{user_headpic} where uid=#{uid}")
    Integer updateUserByID(Integer uid);

    //购买会员后成为会员
    @Update("update user_info set isVip=1 where uid=#{uid}")
    Integer upgrade(Integer uid);

    @Select("select count(*) from user_info")
    Integer countNum();

    @Select("select count(*) from user_info where isVip=1")
    Integer countNumIsVIP();

    @Select("select count(*) from user_info where isVip=0")
    Integer countNumIsNotVIp();

    //添加留言
    @Insert("insert into liuy(name,email,mood,remark)values(#{name},#{email},#{mood},#{remark})")
    Integer addLiuy(LiuY liuY);
    //查询留言
    @Select("select *  from liuy")
    List<LiuY> findLiuy();
}
