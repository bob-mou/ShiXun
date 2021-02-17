package com.example.demo.Service;

import com.example.demo.Entity.Manager;
import com.example.demo.Entity.PublishList;

import java.util.List;

public interface PublishListService {
    List<PublishList> findAll();
    List<PublishList> findAllApply();
    List<PublishList> findAllApplydealwith();
    Integer updatePublishList(PublishList publishlist);
    PublishList findApplyByID(Integer apply_for_publish_ID);

}
