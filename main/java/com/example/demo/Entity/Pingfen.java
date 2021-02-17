package com.example.demo.Entity;

public class Pingfen {
    private Integer pingfenid;
    private Integer rid;
    private String oldresumeName;
    private  Integer hid;
    private Integer pingfenmark;
    private String pingfenremark;
    private Integer hrmark;
    private String hrremark;
    //通过外键查询的HRinfo表中字段
    private String hr_name;

    public String getOldresumeName() {
        return oldresumeName;
    }

    public void setOldresumeName(String oldresumeName) {
        this.oldresumeName = oldresumeName;
    }

    public String getHr_name() {
        return hr_name;
    }

    public void setHr_name(String hr_name) {
        this.hr_name = hr_name;
    }

    public Integer getPingfenid() {
        return pingfenid;
    }

    public void setPingfenid(Integer pingfenid) {
        this.pingfenid = pingfenid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getPingfenmark() {
        return pingfenmark;
    }

    public void setPingfenmark(Integer pingfenmark) {
        this.pingfenmark = pingfenmark;
    }

    public String getPingfenremark() {
        return pingfenremark;
    }

    public void setPingfenremark(String pingfenremark) {
        this.pingfenremark = pingfenremark;
    }

    public Integer getHrmark() {
        return hrmark;
    }

    public void setHrmark(Integer hrmark) {
        this.hrmark = hrmark;
    }

    public String getHrremark() {
        return hrremark;
    }

    public void setHrremark(String hrremark) {
        this.hrremark = hrremark;
    }
}
