package com.example.demo.Service;

import com.example.demo.Entity.Hr;
import com.example.demo.Entity.Pingfen;

import java.util.List;

public interface PinfenService {
    //查找模板评分
    List<Pingfen> findrPinfen();

    //按照ID模糊查找模板评分
    List<Pingfen> findrPinfenByName(String name);

    //按照评分模糊查找模板评分
    List<Pingfen> findrPinfenByPfmark(Integer PF);

    //按照名字和评分模糊查找模板评分
    List<Pingfen> findrPinfenByPfmarkandName(Pingfen pingfen);

    //查找HR评分
    List<Pingfen> findHRPinfen();

    //按照HR姓名模糊查找HR评分
    List<Pingfen> findHRPinfenByName(String hr_name);

    //按照ID模糊查找模板评分
    List<Pingfen> findHRPinfenByPfmark(Integer hrmark);

    //按照HR姓名和评分联合模糊查找HR评分
    List<Pingfen> findHRPinfenByPfmarkandName(String hr_name,Integer hrmark);

    //添加简历评分
    Integer pingfenRe(Pingfen pingfen);

    //添加HR评分
    Integer pingfenHr(Pingfen pingfen);

    Integer avgHR(Integer hid);


}
