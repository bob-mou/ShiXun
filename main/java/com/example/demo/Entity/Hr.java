package com.example.demo.Entity;

import java.sql.Date;

public class Hr {

    private  Integer hid;
    private Date register_date;
    private String hr_account;
    private String hr_name;
    private String hr_password;
    private Boolean hr_sex;
    private String hr_tel;
    private String hr_qq;
    private String hr_headpic;//防止代码报错用的临时代码
    private String hr_headpic1;
    private String hr_headpic2;
    private Boolean isHr;
    private Integer manager_id;
    private String hr_jl;
    private String message;
    private Boolean isdealwith;
    private Integer avgHR;

    public Integer getAvgHR() {
        return avgHR;
    }

    public void setAvgHR(Integer avgHR) {
        this.avgHR = avgHR;
    }

    public Boolean getIsdealwith() {
        return isdealwith;
    }

    public void setIsdealwith(Boolean isdealwith) {
        this.isdealwith = isdealwith;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHr_headpic() {
        return hr_headpic;
    }

    public void setHr_headpic(String hr_headpic) {
        this.hr_headpic = hr_headpic;
    }

    public Boolean getIsHr() {
        return isHr;
    }

    public void setIsHr(Boolean isHr) {
        this.isHr = isHr;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public String getHr_account() {
        return hr_account;
    }

    public void setHr_account(String hr_account) {
        this.hr_account = hr_account;
    }

    public String getHr_name() {
        return hr_name;
    }

    public void setHr_name(String hr_name) {
        this.hr_name = hr_name;
    }

    public String getHr_password() {
        return hr_password;
    }

    public void setHr_password(String hr_password) {
        this.hr_password = hr_password;
    }

    public Boolean getHr_sex() {
        return hr_sex;
    }

    public void setHr_sex(Boolean hr_sex) {
        this.hr_sex = hr_sex;
    }

    public String getHr_tel() {
        return hr_tel;
    }

    public void setHr_tel(String hr_tel) {
        this.hr_tel = hr_tel;
    }

    public String getHr_qq() {
        return hr_qq;
    }

    public void setHr_qq(String hr_qq) {
        this.hr_qq = hr_qq;
    }

    public String getHr_headpic1() {
        return hr_headpic1;
    }

    public void setHr_headpic1(String hr_headpic1) {
        this.hr_headpic1 = hr_headpic1;
    }

    public String getHr_headpic2() {
        return hr_headpic2;
    }

    public void setHr_headpic2(String hr_headpic2) {
        this.hr_headpic2 = hr_headpic2;
    }

    public String getHr_jl() {
        return hr_jl;
    }

    public void setHr_jl(String hr_jl) {
        this.hr_jl = hr_jl;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }
}
