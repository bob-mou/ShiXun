package com.example.demo.Service;

import com.example.demo.Entity.Vip;

import java.util.List;

public interface VipService {
    List<Vip> findAllVip();
    Vip findVipByID(Integer vid);
    Integer deleteVipByID(Integer vid);
    Integer updateVip(Vip vip);
}
