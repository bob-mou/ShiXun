package com.example.demo.Entity;

import java.sql.Date;

public class Manager {

    private  Integer mid;
    private String manager_account;
    private  Integer power;
    private Date manager_date;
    private String manager_name;
    private String manager_password;
    private Boolean manager_sex;
    private String manager_tel;
    private String manager_address;
    private String manager_headpic;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getManager_account() {
        return manager_account;
    }

    public void setManager_account(String manager_account) {
        this.manager_account = manager_account;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Date getManager_date() {
        return manager_date;
    }

    public void setManager_date(Date manager_date) {
        this.manager_date = manager_date;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getManager_password() {
        return manager_password;
    }

    public void setManager_password(String manager_password) {
        this.manager_password = manager_password;
    }

    public Boolean getManager_sex() {
        return manager_sex;
    }

    public void setManager_sex(Boolean manager_sex) {
        this.manager_sex = manager_sex;
    }

    public String getManager_tel() {
        return manager_tel;
    }

    public void setManager_tel(String manager_tel) {
        this.manager_tel = manager_tel;
    }

    public String getManager_address() {
        return manager_address;
    }

    public void setManager_address(String manager_address) {
        this.manager_address = manager_address;
    }

    public String getManager_headpic() {
        return manager_headpic;
    }

    public void setManager_headpic(String manager_headpic) {
        this.manager_headpic = manager_headpic;
    }
}
