package com.example.demo.Service;

import com.example.demo.Entity.Flw;

import java.sql.Date;
import java.util.List;

public interface FlwInfoService {
    //添加订单
    Integer addFlw(Flw flw);

    //查询所有订单
    List<Flw> findAllFlw();


    Integer delFlw(Integer id);
}
