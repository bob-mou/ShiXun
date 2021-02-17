package com.example.demo.Service;

import com.example.demo.Entity.VocationInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface VocationService {
    List<VocationInfo> findAllVoc();
    VocationInfo findVocationByID(Integer vocation_id);
    Integer deleteVocationByID(Integer vocation_id);
    Integer insertVocation(String vocation_name);
    Integer updateVocation(VocationInfo vocationinfo);
    PageInfo<VocationInfo> findAllVocFY(Integer pageIndex, Integer pageSize);
}
