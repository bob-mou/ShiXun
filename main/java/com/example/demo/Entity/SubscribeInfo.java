package com.example.demo.Entity;

import java.sql.Date;

public class SubscribeInfo {
    private Integer subscribe_id;
    private Integer user_id;
    private String user_name;
    private Integer hr_id;
    private String hr_name;
    private Date subscribe_date;
    private String userAndMessage;
    private Integer vocation_id;
    private String vocation_name;
    private Integer isdealwith;//请求信息的状态
    private String oldName;

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getHr_name() {
        return hr_name;
    }

    public void setHr_name(String hr_name) {
        this.hr_name = hr_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getVocation_name() {
        return vocation_name;
    }

    public void setVocation_name(String vocation_name) {
        this.vocation_name = vocation_name;
    }

    public Integer getIsdealwith() {
        return isdealwith;
    }
    public void setIsdealwith(Integer isdealwith) {
        this.isdealwith = isdealwith;
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

    private String resume_download_address;



    public Integer getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(Integer subscribe_id) {
        this.subscribe_id = subscribe_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getHr_id() {
        return hr_id;
    }

    public void setHr_id(Integer hr_id) {
        this.hr_id = hr_id;
    }

    public Date getSubscribe_date() {
        return subscribe_date;
    }

    public void setSubscribe_date(Date subscribe_date) {
        this.subscribe_date = subscribe_date;
    }

    public String getUserAndMessage() {
        return userAndMessage;
    }

    public void setUserAndMessage(String userAndMessage) {
        this.userAndMessage = userAndMessage;
    }
}
