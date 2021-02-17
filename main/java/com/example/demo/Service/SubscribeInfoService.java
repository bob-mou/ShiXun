package com.example.demo.Service;

import com.example.demo.Entity.SubscribeInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SubscribeInfoService{

    //查询所有定制信息
    PageInfo<SubscribeInfo> findAllSubscribeInfo(Integer pageIndex, Integer pageSize);

    //删除一条信息
    Integer deleteSubscribeInfoByID(Integer subscribe_id);

    //插入一条语句
    Integer inster(SubscribeInfo subscribeInfo);

    List<SubscribeInfo> findAllByHRID( Integer hr_id);

    Integer updateisdealwith(Integer subscribe_id,Integer isdealwith);

    List<SubscribeInfo> findAllByUserID( Integer uid);

    SubscribeInfo findAllByID( Integer subscribe_id);
    //修改简历
    Integer updateResum(SubscribeInfo subscribeInfo);
    //查询没处理的条数
    Integer countDealWith();
}
