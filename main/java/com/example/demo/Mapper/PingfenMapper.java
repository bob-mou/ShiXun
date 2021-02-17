package com.example.demo.Mapper;

import com.example.demo.Entity.Hr;
import com.example.demo.Entity.Pingfen;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PingfenMapper {
    //查找模板评分
    @Select("select pingfen.pingfenid,pingfen.rid,pingfen.pingfenmark,pingfen.pingfenremark,resumeinfo.oldresumeName  from pingfen join resumeInfo on resumeInfo.rid=pingfen.rid ")
    List<Pingfen> findrPinfen();

    //按照名字模糊查找模板评分
    @Select("select pingfen.pingfenid,pingfen.rid,pingfen.pingfenmark,pingfen.pingfenremark,resumeinfo.oldresumeName  from pingfen join resumeInfo on resumeInfo.rid=pingfen.rid where resumeInfo.oldresumeName like '%${name}%'")
    List<Pingfen> findrPinfenByName(String name);

    //按照评分模糊查找模板评分
    @Select("select pingfen.pingfenid,pingfen.rid,pingfen.pingfenmark,pingfen.pingfenremark,resumeinfo.oldresumeName  from pingfen join resumeInfo on resumeInfo.rid=pingfen.rid where pingfen.pingfenmark=#{PF}")
    List<Pingfen> findrPinfenByPfmark(Integer PF);

    //按照ID和评分模糊查找模板评分
    @Select("select pingfen.pingfenid,pingfen.rid,pingfen.pingfenmark,pingfen.pingfenremark,resumeinfo.oldresumeName  from pingfen join resumeInfo on resumeInfo.rid=pingfen.rid where pingfen.pingfenmark=#{pingfenmark} AND resumeInfo.oldresumeName like '%${oldresumeName}%'")
    List<Pingfen> findrPinfenByPfmarkandName(Pingfen pingfen);

    //查找HR评分
    @Select("select pingfen.pingfenid,hr_info.hr_name,pingfen.hrmark,pingfen.hrremark from pingfen join hr_info on hr_info.hid=pingfen.hid")
    List<Pingfen> findHRPinfen();

    //按照HR姓名模糊查找HR评分
    @Select("select pingfen.pingfenid,hr_info.hr_name,pingfen.hrmark,pingfen.hrremark from pingfen join hr_info on hr_info.hid=pingfen.hid where hr_info.hr_name like '%${hr_name}%'")
    List<Pingfen> findHRPinfenByName(String hr_name);

    //按照ID模糊查找模板评分
    @Select("select pingfen.pingfenid,hr_info.hr_name,pingfen.hrmark,pingfen.hrremark from pingfen join hr_info on hr_info.hid=pingfen.hid where pingfen.hrmark=#{hrmark}")
    List<Pingfen> findHRPinfenByPfmark(Integer hrmark);

    //按照HR姓名和评分联合模糊查找HR评分
    @Select("select pingfen.pingfenid,hr_info.hr_name,pingfen.hrmark,pingfen.hrremark from pingfen join hr_info on hr_info.hid=pingfen.hid where pingfen.hrmark=#{hrmark} AND hr_info.hr_name like '%${hr_name}%'")
    List<Pingfen> findHRPinfenByPfmarkandName(String hr_name,Integer hrmark);

    //添加评分
    @Insert("insert into pingfen(rid,pingfenmark,pingfenremark)values(#{rid},#{pingfenmark},#{pingfenremark})")
    Integer pingfenRe(Pingfen pingfen);

    @Insert("insert into pingfen(hid,hrmark,hrremark)values(#{hid},#{hrmark},#{hrremark})")
    Integer pingfenHr(Pingfen pingfen);

    //计算HR的评分的平均分
    @Select("SELECT avg(hrmark) FROM pingfen where hid= #{hid};")
    Integer avgHR(Integer hid);
    //HR推荐页面会用到
    @Select("select avg(pingfen.hrmark)as avgHR,hid from pingfen where pingfen.hid!='null' group by hid")
    List<Hr> allavgHR();
}
