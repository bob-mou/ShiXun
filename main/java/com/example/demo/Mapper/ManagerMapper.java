package com.example.demo.Mapper;

import com.example.demo.Entity.Manager;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ManagerMapper {
    //登录
    @Select("SELECT * FROM manager_info where manager_account=#{manager_account} and manager_password=#{manager_password} and power=#{power}")
    Manager ManagerLogin(Manager manager);

    //后台档案管理(查找所有管理员信息)
    @Select("SELECT * FROM manager_info")
    List<Manager> managerFile();

    //按照ID号查找管理员
    @Select("SELECT * FROM manager_info where mid=#{mid}")
    Manager findManagerByID(Integer mid);

    //按照账号查找管理员
    @Select("SELECT * FROM manager_info where manager_account like '%${manager_account}%'")
    List<Manager> findManagerByAccount(String manager_account);

    //按照日期查找管理员
    @Select("SELECT * FROM manager_info where manager_date=#{manager_date}")
    List<Manager> findManagerByDate(Date manager_date);

    //按照真实姓名查找管理员
    @Select("SELECT * FROM manager_info where manager_name like '%${manager_name}%'")
    List<Manager> findManagerByName(String manager_name);

    //删除一个管理员信息
    @Delete("DELETE FROM manager_info WHERE mid=#{mid}")
    Integer deleteManagerByID(Integer mid);

    //添加管理者信息
    @Insert("INSERT INTO manager_info ( `power`, `manager_account`, `manager_date`, `manager_name`, `manager_password`, `manager_sex`, `manager_tel`, `manager_address`, `manager_headpic`) VALUES (#{power}, #{manager_account}, #{manager_date}, #{manager_name}, #{manager_password}, #{manager_sex}, #{manager_tel}, #{manager_address},#{manager_headpic});")
    Integer insertManager(Manager manager);

    //更新管理员信息
    @Update("UPDATE manager_info SET power=#{power}, manager_account=#{manager_account}, manager_date=#{manager_date}, manager_name=#{manager_name}, manager_password=#{manager_password}, manager_sex=#{manager_sex}, manager_tel=#{manager_tel}, manager_address=#{manager_address}, manager_headpic=#{manager_headpic} WHERE mid=#{mid};")
    Integer updateManager(Manager manager);

    //查询管理员人数
    @Select("SELECT count(*) FROM manager_info")
    Integer countAllManagerNum();

}
