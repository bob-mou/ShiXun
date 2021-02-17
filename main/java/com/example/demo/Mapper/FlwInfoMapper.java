package com.example.demo.Mapper;

import com.example.demo.Entity.Flw;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

@Mapper
public interface FlwInfoMapper {
    //添加订单
    @Insert("insert into flw_info(id,sum,body,idName,user_account,pay_date)values(#{id},#{sum},#{body},#{idName},#{user_account},sysdate())")
    Integer addFlw(Flw flw);
    //查询所有订单
    @Select("select * from flw_info")
    List<Flw> findAllFlw();


    @Delete("DELETE FROM flw_info WHERE fid = #{id}")
    Integer delFlw(Integer id);
}
