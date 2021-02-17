package com.example.demo.Serviceimpl;



import com.example.demo.Entity.VocationInfo;
import com.example.demo.Mapper.VocationMapper;
import com.example.demo.Service.VocationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocationServiceimpl implements VocationService {
    @Autowired
    private VocationMapper vocationMapper;

    @Override
    public PageInfo<VocationInfo> findAllVocFY(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<VocationInfo> list = vocationMapper.findAllVoc();
        PageInfo<VocationInfo> info = new PageInfo<>(list);
        return  info;
    }

    @Override
    public List<VocationInfo> findAllVoc() {
        return vocationMapper.findAllVoc();
    }

    @Override
    public VocationInfo findVocationByID(Integer vocation_id) { return vocationMapper.findVocationByID(vocation_id); }

    @Override
    public Integer deleteVocationByID(Integer vocation_id) { return  vocationMapper.deleteVocationByID(vocation_id); }

    @Override
    public Integer insertVocation(String vocation_name) { return vocationMapper.insertVocation(vocation_name); }

    @Override
    public Integer updateVocation(VocationInfo vocationinfo) { return vocationMapper.updateVocation(vocationinfo); }



}
