package com.example.demo.Mapper;


import com.example.demo.Entity.PublishList;
import com.example.demo.Entity.ResumeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PublishListMapper {
    //所有未处理的信息
    @Select("SELECT apply_for_publishList.*,resumeinfo.oldresumeName FROM apply_for_publishlist join resumeinfo on resumeinfo.rid=apply_for_publishlist.resume_id  where apply_for_publishList.isdealwith=0  order by resume_apply_date desc")
    List<PublishList> findAll();

    //查询已经处理的信息
    @Select("SELECT apply_for_publishList.* , manager_info.manager_name,resumeinfo.oldresumeName FROM apply_for_publishlist join manager_info on manager_info.mid=apply_for_publishlist.manager_id  join resumeinfo on resumeinfo.rid=apply_for_publishlist.resume_id where isdealwith=1 order by resume_apply_date desc")
    List<PublishList> findAllApply();


    //所有已发布
    @Select("SELECT * FROM apply_for_publishList where isdealwith=1")
    List<PublishList> findAllApplydealwith();

    //更新内容
    @Update("UPDATE apply_for_publishlist SET manager_id=#{manager_id}, resume_publish_date=#{resume_publish_date}, isPublished=#{isPublished}, apply_message=#{apply_message}, isdealwith=#{isdealwith} WHERE apply_for_publish_ID=#{apply_for_publish_ID}")
    Integer updatePublishList(PublishList publishlist);

    @Select("SELECT * FROM apply_for_publishList where apply_for_publish_ID=#{apply_for_publish_ID}")
    PublishList findApplyByID(Integer apply_for_publish_ID);

}
