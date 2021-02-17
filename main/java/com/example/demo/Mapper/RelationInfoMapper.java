package com.example.demo.Mapper;

import com.example.demo.Entity.RelationInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RelationInfoMapper {

    @Select("select * from relationinfo")
    List<RelationInfo> findAllRel();
    @Delete("delete from relationinfo where logid=#{logid}")
    Integer deleteRelByID(Integer logid);
}
