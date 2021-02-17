package com.example.demo.Entity;

import java.sql.Date;

public class PublishList {
    private Integer apply_for_publish_ID;
    private Integer resume_id;
    private String oldresumeName;//简历中的名字
    private Integer manager_id;
    private String manager_name;
    private Date resume_apply_date;
    private Date resume_publish_date;
    private Boolean isPublished;//注意调用时的名称
    private String apply_message;
    private Boolean isdealwith; //标记申请是否处理

    public String getOldresumeName() {
        return oldresumeName;
    }

    public void setOldresumeName(String oldresumeName) {
        this.oldresumeName = oldresumeName;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public Boolean getIsdealwith() {
        return isdealwith;
    }

    public void setIsdealwith(Boolean isdealwith) {
        this.isdealwith = isdealwith;
    }

    public Integer getApply_for_publish_ID() {
        return apply_for_publish_ID;
    }

    public void setApply_for_publish_ID(Integer apply_for_publish_ID) {
        this.apply_for_publish_ID = apply_for_publish_ID;
    }

    public Integer getResume_id() {
        return resume_id;
    }

    public void setResume_id(Integer resume_id) {
        this.resume_id = resume_id;
    }


    public Date getResume_apply_date() {
        return resume_apply_date;
    }

    public void setResume_apply_date(Date resume_apply_date) {
        this.resume_apply_date = resume_apply_date;
    }

    public Date getResume_publish_date() {
        return resume_publish_date;
    }

    public void setResume_publish_date(Date resume_publish_date) {
        this.resume_publish_date = resume_publish_date;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public String getApply_message() {
        return apply_message;
    }

    public void setApply_message(String apply_message) {
        this.apply_message = apply_message;
    }
}
