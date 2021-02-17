package com.example.demo.Mapper;

import com.example.demo.Entity.Manager;
import com.example.demo.Entity.SubscribeInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SubscribeInfoMapper {

    @Select("SELECT subscribeinfo.*,user_info.user_name,vocationinfo.vocation_name,hr_info.hr_name  FROM subscribeinfo   join user_info on user_info.uid=subscribeinfo.user_id join vocationinfo on vocationinfo.vocation_id=subscribeinfo.vocation_id   join hr_info on hr_info.hid=subscribeinfo.hr_id     order by subscribeinfo.subscribe_date desc")
    List<SubscribeInfo> findAllSubscribeInfo();

    //删除一条信息
    @Delete("DELETE FROM subscribeinfo WHERE subscribe_id=#{subscribe_id}")
    Integer deleteSubscribeInfoByID(Integer subscribe_id);

    @Insert("INSERT INTO `subscribeinfo` (`subscribe_date`, `user_id`, `hr_id`, `userAndMessage`, `resume_download_address`, `vocation_id`, `oldName`) VALUES (#{subscribe_date}, #{user_id}, #{hr_id}, #{userAndMessage}, #{resume_download_address}, #{vocation_id}, #{oldName})")
    Integer inster(SubscribeInfo subscribeInfo);

    @Select("SELECT subscribeinfo.*,user_info.user_name,vocationinfo.vocation_name FROM subscribeinfo  join user_info on user_info.uid=subscribeinfo.user_id join vocationinfo on vocationinfo.vocation_id=subscribeinfo.vocation_id    where hr_id=#{hr_id} order by subscribeinfo.subscribe_date desc")
    List<SubscribeInfo> findAllByHRID( Integer hr_id);

    //根据用户id搜索预约信息
    @Select("SELECT subscribeinfo.*,hr_info.hr_name,vocationinfo.vocation_name FROM subscribeinfo  join hr_info on hr_info.hid=subscribeinfo.hr_id join vocationinfo on vocationinfo.vocation_id=subscribeinfo.vocation_id    where user_id=#{uid} order by subscribeinfo.subscribe_date desc")
    List<SubscribeInfo> findAllByUserID( Integer uid);

    //更新处理信息
    @Update("UPDATE `subscribeinfo` SET `isdealwith`=#{isdealwith} WHERE `subscribe_id`=#{subscribe_id}")
    Integer updateisdealwith(Integer subscribe_id,Integer isdealwith);

    @Select("SELECT subscribeinfo.*,hr_info.hr_name,vocationinfo.vocation_name FROM subscribeinfo  join hr_info on hr_info.hid=subscribeinfo.hr_id join vocationinfo on vocationinfo.vocation_id=subscribeinfo.vocation_id     where subscribe_id=#{subscribe_id} ")
    SubscribeInfo findAllByID( Integer subscribe_id);

    @Update("UPDATE  `subscribeinfo` SET   oldName=#{oldName},   resume_download_address=#{resume_download_address}  WHERE `subscribe_id`=#{subscribe_id} ")
    Integer Update(SubscribeInfo subscribeInfo);

    @Update("UPDATE  `subscribeinfo` SET   oldName=#{oldName},   resume_download_address=#{resume_download_address}  WHERE `subscribe_id`=#{subscribe_id} ")
    Integer updateResum(SubscribeInfo subscribeInfo);

    //查询不是HR的条数
    @Select("SELECT count(*) FROM hr_info where isdealwith=false")
    Integer countDealWith();

}
