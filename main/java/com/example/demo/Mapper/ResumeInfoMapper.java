package com.example.demo.Mapper;

import com.example.demo.Entity.ResumeInfo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ResumeInfoMapper {
    @Select("select res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id")
    List<ResumeInfo> findAllResumeInfo();

    @Select("select res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where rid=#{rid}")
    ResumeInfo findAllResumeInfoByID(Integer rid);

    @Insert("insert into resumeinfo(resume_up_date,vocation_id,hid,resume_download_address,download_mini_address,remark,oldresumeName,resumeName,resume_times,state)  values(sysdate(),#{vocation_id},#{hid},#{resume_download_address},#{download_mini_address},#{remark},#{oldresumeName},#{resumeName},#{resume_times},'未发布')")
    Integer hruploadRe(ResumeInfo resumeInfo);

    @Select("select res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where hid=#{hr_account}")
    List<ResumeInfo> findAllResumeInfoByAccount(String hr_account);

    @Update("update resumeinfo set resume_times = #{resume_times} where rid=#{rid}")
    void updateTimes(ResumeInfo resumeInfo);

    @Update("update resumeinfo set state = '未发布' where rid=#{rid}")
    Integer XJResumeByID(Integer rid);

    //根据行业模糊查询
    @Select("select resumeinfo.* from resumeinfo join vocationinfo on resumeinfo.vocation_id=vocationinfo.vocation_id where vocationinfo.vocation_name like '%${vocation_name}%'")
    List<ResumeInfo> findAllResumeInfoByVocation(String vocation_name);

    //更新发布状态
    @Update("update resumeinfo set state = '发布处理中' where rid=#{rid}")
    void updateState(Integer rid);

    @Select("select res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where state='已发布'")
    List<ResumeInfo> findResumeInfo();

    //前台查询已发布的简历信息（包括行业号）
    @Select("select resumeinfo.*,vocation_name from resumeinfo join vocationinfo on resumeinfo.vocation_id=vocationinfo.vocation_id where vocationinfo.vocation_id = #{vocation_id} and resumeinfo.state='已发布'")
    List<ResumeInfo> findResumeInfoByID(Integer vocation_id);

    //查询等待处理的模板的数量
    @Select("SELECT count(*) FROM resumeinfo where state='发布处理中'")
    Integer countResume();

    @Update("update resumeinfo set state =#{state},resume_publish_date=#{resume_publish_date} where rid=#{rid}")
    Integer updatestate(ResumeInfo resumeInfo);

    @Select("SELECT  res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where state='已发布' order by resume_up_date desc")
    List<ResumeInfo> findAllResumeInfoByDate();

    @Select("SELECT  res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where state='已发布' order by resume_times desc")
    List<ResumeInfo> findAllResumeInfoByTimes();

    @Select("SELECT  res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where state='已发布' order by resume_times desc")
    List<ResumeInfo> findAllResumeInfoByFenshu();

    @Select("SELECT count(*) FROM resumeinfo")
    Integer countResumeNum();

    @Select("SELECT sum(resume_times) FROM resumeinfo")
    Integer countAllResume();

    //查询寻排名前十的简历模板的信息
    @Select("SELECT * FROM resumeinfo  order by resume_times desc limit 0,10")
    List<ResumeInfo> finResumeByResumeTimes();

    //职业需求前十
    @Select("SELECT resumeinfo.*,vocationinfo.vocation_name FROM resumeinfo join vocationinfo on vocationinfo.vocation_id=resumeinfo.vocation_id group by vocationinfo.vocation_id order by  resumeinfo.resume_times desc limit 0,10")
    List<ResumeInfo> finResumeByVocationTimes();

    //按简历名字查询
    @Select("SELECT  res.*,vocation_name from resumeinfo res join vocationinfo voc on res.vocation_id = voc.vocation_id where state='已发布' and res.remark like '%${reName}%'")
    List<ResumeInfo> findResumeInfoByName(String reName);

}
