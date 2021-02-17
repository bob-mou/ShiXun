package com.example.demo.Serviceimpl;
import com.example.demo.Entity.Vip;
import com.example.demo.Mapper.VipMapper;
import com.example.demo.Service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipServiceimpl implements VipService {

    @Autowired
    private VipMapper vipMapper;

    @Override
    public List<Vip> findAllVip() {
        return vipMapper.findAllVip();
    }

    @Override
    public Vip findVipByID(Integer vid) { return vipMapper.findVipByID(vid); }

    @Override
    public Integer deleteVipByID(Integer vid) {
        return vipMapper.deleteVipByID(vid);
    }

    @Override
    public Integer updateVip(Vip vip) { return vipMapper.updateVip(vip); }

}
