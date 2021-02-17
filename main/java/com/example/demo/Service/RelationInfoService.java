package com.example.demo.Service;

import com.example.demo.Entity.RelationInfo;

import java.util.List;

public interface RelationInfoService {
    List<RelationInfo> findAllRel();
    Integer deleteRelByID(Integer logid);
}
