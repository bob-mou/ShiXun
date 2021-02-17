package com.example.demo.Controller;

import com.example.demo.Entity.RelationInfo;
import com.example.demo.Service.RelationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("relationinfo")
public class RelationInfoController {

    @Autowired
    private RelationInfoService relationInfoService;

    @GetMapping("/index")
    public String ListRelPage() {
        List<RelationInfo> lists = relationInfoService.findAllRel();
        return "reiation_ht";
    }
}
