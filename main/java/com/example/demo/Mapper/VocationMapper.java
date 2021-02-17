package com.example.demo.Mapper;


import com.example.demo.Entity.Manager;
import com.example.demo.Entity.VocationInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VocationMapper {
    @Select("SELECT * FROM vocationinfo")
    List<VocationInfo> findAllVoc();
    @Select("SELECT * FROM vocationinfo where vocation_id=#{vocation_id}")
    VocationInfo findVocationByID(Integer vocation_id);
    @Delete("DELETE FROM vocationinfo WHERE vocation_id=#{vocation_id}")
    Integer deleteVocationByID(Integer vocation_id);

    @Insert("INSERT INTO vocationinfo (`vocation_name`) VALUES (#{vocation_name});")
    Integer insertVocation(String vocation_name);

    @Update("UPDATE vocationinfo SET  vocation_name=#{vocation_name} WHERE vocation_id=#{vocation_id};")
    Integer updateVocation(VocationInfo vocationinfo);


}

