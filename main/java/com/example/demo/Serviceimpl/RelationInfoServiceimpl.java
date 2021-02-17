package com.example.demo.Serviceimpl;

import com.example.demo.Entity.RelationInfo;
import com.example.demo.Service.RelationInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationInfoServiceimpl implements RelationInfoService {

    @Override
    public List<RelationInfo> findAllRel() {
        return null;
    }

    @Override
    public Integer deleteRelByID(Integer logid) {
        return null;
    }
}
