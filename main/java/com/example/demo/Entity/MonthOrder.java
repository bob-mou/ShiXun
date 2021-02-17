package com.example.demo.Entity;

import java.util.Date;

public class MonthOrder {
    private String date;//月份
    private String total;//每月总订单数

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
