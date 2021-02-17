package com.example.demo.Mapper;

import com.example.demo.Entity.Vip;
import com.example.demo.Entity.VocationInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface VipMapper {

    @Select("select * from vip_info")
    List<Vip> findAllVip();
    @Select("SELECT * FROM Vip where vid=#{vid}")
    Vip findVipByID(Integer vid);
    @Delete("delete from vip_info where vid=#{vid}")
    Integer deleteVipByID(Integer vid);
    @Update("UPDATE Vip SET  vtime=#{vtime}, price=#{price}, vname=#{vname} WHERE vid=#{vid};")
    Integer updateVip(Vip vip);
}
