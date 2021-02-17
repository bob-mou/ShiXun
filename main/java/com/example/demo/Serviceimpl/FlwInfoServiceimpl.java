package com.example.demo.Serviceimpl;


import com.example.demo.Entity.Flw;
import com.example.demo.Mapper.FlwInfoMapper;
import com.example.demo.Service.FlwInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FlwInfoServiceimpl implements FlwInfoService {
    @Autowired
    FlwInfoMapper flwInfoMapper;


    @Override
    public Integer addFlw(Flw flw) {
        return flwInfoMapper.addFlw(flw);
    }

    @Override
    public List<Flw> findAllFlw() {
        return flwInfoMapper.findAllFlw();
    }

    @Override
    public Integer delFlw(Integer id) {
        return flwInfoMapper.delFlw(id);
    }
}
