package com.example.demo.Serviceimpl;

import com.example.demo.Entity.Manager;
import com.example.demo.Entity.PublishList;
import com.example.demo.Entity.ResumeInfo;
import com.example.demo.Mapper.PublishListMapper;
import com.example.demo.Service.PublishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishListServiceimpl implements PublishListService {
    @Autowired
    private PublishListMapper publishListMapper;

    @Override
    public List<PublishList> findAll() {
        return publishListMapper.findAll();
    }

    @Override
    public List<PublishList> findAllApply() {
        return publishListMapper.findAllApply();
    }

    @Override
    public List<PublishList> findAllApplydealwith() {
        return publishListMapper.findAllApplydealwith();
    }

    @Override
    public Integer updatePublishList(PublishList publishlist) {
        return publishListMapper.updatePublishList(publishlist);
    }

    @Override
    public PublishList findApplyByID(Integer apply_for_publish_ID) {
        return publishListMapper.findApplyByID(apply_for_publish_ID);
    }
}
