package com.example.demo.Service;

import com.example.demo.Entity.ResumeInfo;

import java.util.List;

public interface ResumeInfoService {
    List<ResumeInfo> findAllResumeInfo();
    ResumeInfo findAllResumeInfoByID(Integer rid);
    Integer hruploadRe(ResumeInfo resumeInfo);
    List<ResumeInfo> findAllResumeInfoByAccount(String hr_account);
    //更新下载次数
    void updateTimes(ResumeInfo resumeInfo);
    Integer XJResumeByID(Integer rid);
    List<ResumeInfo> findAllResumeInfoByVocation(String vocation_name);
    //更新状态
    void updateState(Integer rid);
    List<ResumeInfo> findResumeInfoByID(Integer vocation_id);
    List<ResumeInfo> findResumeInfo();
    //查询等待处理的模板的数量
    Integer countResume();
    //更新简历处理状态
    Integer updatestate(ResumeInfo resumeInfo);

    List<ResumeInfo> findResumeInfoByDate();

    List<ResumeInfo> findResumeInfoByTimes();

    List<ResumeInfo> findResumeInfoByFenshu();

    Integer countAllResume();

    List<ResumeInfo> findResumeInfoByName(String reName);

    Integer countResumeNum();

    List<ResumeInfo> finResumeByResumeTimes();

    List<ResumeInfo> finResumeByVocationTimes();
}
