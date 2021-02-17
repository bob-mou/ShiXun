package com.example.demo.Serviceimpl;

import com.example.demo.Entity.SubscribeInfo;
import com.example.demo.Entity.SysLog;
import com.example.demo.Mapper.SubscribeInfoMapper;
import com.example.demo.Service.SubscribeInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeInfoServiceimpl  implements SubscribeInfoService{
    @Autowired
    private SubscribeInfoMapper subscribeInfoMapper;

    @Override
    public PageInfo<SubscribeInfo> findAllSubscribeInfo(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SubscribeInfo> list = subscribeInfoMapper.findAllSubscribeInfo();
        PageInfo<SubscribeInfo> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Integer deleteSubscribeInfoByID(Integer subscribe_id) {
        return subscribeInfoMapper.deleteSubscribeInfoByID(subscribe_id);
    }

    @Override
    public Integer inster(SubscribeInfo subscribeInfo) {
        return subscribeInfoMapper.inster(subscribeInfo);
    }

    @Override
    public List<SubscribeInfo> findAllByHRID(Integer hr_id) {
        return subscribeInfoMapper.findAllByHRID(hr_id);
    }

    @Override
    public Integer updateisdealwith(Integer subscribe_id,Integer isdealwith) {
        return subscribeInfoMapper.updateisdealwith(subscribe_id,isdealwith);
    }

    @Override
    public List<SubscribeInfo> findAllByUserID(Integer uid) {
        return subscribeInfoMapper.findAllByUserID(uid);
    }

    @Override
    public SubscribeInfo findAllByID(Integer subscribe_id) {
        return subscribeInfoMapper.findAllByID(subscribe_id);
    }

    @Override
    public Integer updateResum(SubscribeInfo subscribeInfo) {
        return subscribeInfoMapper.updateResum(subscribeInfo);
    }

    @Override
    public Integer countDealWith() {
        return subscribeInfoMapper.countDealWith();
    }

}
