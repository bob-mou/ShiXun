package com.example.demo.Entity;

import java.sql.Date;

public class ResumeInfo {
    private Integer rid;
    private Date resume_up_date;
    private Date resume_publish_date;
    private Integer vocation_id;
    private String resume_download_address;
    private String hid ;
    private Integer resume_times;
    private String download_mini_address;
    private String remark;
    private String oldresumeName;
    private String resumeName;
    private String vocation_name;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVocation_name() {
        return vocation_name;
    }

    public void setVocation_name(String vocation_name) {
        this.vocation_name = vocation_name;
    }

    public String getOldresumeName() {
        return oldresumeName;
    }

    public void setOldresumeName(String oldresumeName) {
        this.oldresumeName = oldresumeName;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getResume_up_date() {
        return resume_up_date;
    }

    public void setResume_up_date(Date resume_up_date) {
        this.resume_up_date = resume_up_date;
    }

    public Date getResume_publish_date() {
        return resume_publish_date;
    }

    public void setResume_publish_date(Date resume_publish_date) {
        this.resume_publish_date = resume_publish_date;
    }

    public Integer getVocation_id() {
        return vocation_id;
    }

    public void setVocation_id(Integer vocation_id) {
        this.vocation_id = vocation_id;
    }

    public String getResume_download_address() {
        return resume_download_address;
    }

    public void setResume_download_address(String resume_download_address) {
        this.resume_download_address = resume_download_address;
    }

    public Integer getResume_times() {
        return resume_times;
    }

    public void setResume_times(Integer resume_times) {
        this.resume_times = resume_times;
    }

    public String getDownload_mini_address() {
        return download_mini_address;
    }

    public void setDownload_mini_address(String download_mini_address) {
        this.download_mini_address = download_mini_address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
