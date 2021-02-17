package com.example.demo.Serviceimpl;

import com.example.demo.Entity.ResumeInfo;
import com.example.demo.Mapper.ResumeInfoMapper;
import com.example.demo.Service.ResumeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeInfoServiceimpl implements ResumeInfoService {
    @Autowired
    private ResumeInfoMapper resumeInfoMapper;
    @Override
    public List<ResumeInfo> findAllResumeInfo() {
        return resumeInfoMapper.findAllResumeInfo();
    }

    @Override
    public ResumeInfo findAllResumeInfoByID(Integer rid) {
        return resumeInfoMapper.findAllResumeInfoByID(rid);
    }

    @Override
    public Integer hruploadRe(ResumeInfo resumeInfo) {
        resumeInfo.setResume_times(0);
        return resumeInfoMapper.hruploadRe(resumeInfo);
    }

    @Override
    public List<ResumeInfo> findAllResumeInfoByAccount(String hr_account) {
        return resumeInfoMapper.findAllResumeInfoByAccount(hr_account);
    }

    @Override
    public void updateTimes(ResumeInfo resumeInfo) {
        resumeInfoMapper.updateTimes(resumeInfo);
    }

    @Override
    public Integer XJResumeByID(Integer rid) {
        return resumeInfoMapper.XJResumeByID(rid);
    }

    @Override
    public List<ResumeInfo> findAllResumeInfoByVocation(String vocation_name) {
        return resumeInfoMapper.findAllResumeInfoByVocation(vocation_name);
    }

    @Override
    public void updateState(Integer rid) {
        resumeInfoMapper.updateState(rid);
    }

    @Override
    public List<ResumeInfo> findResumeInfoByID(Integer vocation_id) {
        return resumeInfoMapper.findResumeInfoByID(vocation_id);
    }

    @Override
    public List<ResumeInfo> findResumeInfo() {
        return resumeInfoMapper.findResumeInfo();
    }

    @Override
    public Integer countResume() {
        return resumeInfoMapper.countResume();
    }

    @Override
    public Integer updatestate(ResumeInfo resumeInfo) {
        return resumeInfoMapper.updatestate(resumeInfo);
    }

    @Override
    public List<ResumeInfo> findResumeInfoByDate() {
        return resumeInfoMapper.findAllResumeInfoByDate();
    }

    @Override
    public List<ResumeInfo> findResumeInfoByTimes() {
        return resumeInfoMapper.findAllResumeInfoByTimes();
    }

    @Override
    public List<ResumeInfo> findResumeInfoByFenshu() {
        return resumeInfoMapper.findAllResumeInfoByFenshu();
    }

    @Override
    public Integer countAllResume() {
        return resumeInfoMapper.countAllResume();
    }

    @Override
    public List<ResumeInfo> findResumeInfoByName(String reName) {
        return resumeInfoMapper.findResumeInfoByName(reName);
    }

    @Override
    public Integer countResumeNum() {
        return resumeInfoMapper.countResumeNum();
    }

    @Override
    public List<ResumeInfo> finResumeByResumeTimes() {
        return resumeInfoMapper.finResumeByResumeTimes();
    }

    @Override
    public List<ResumeInfo> finResumeByVocationTimes() {
        return resumeInfoMapper.finResumeByVocationTimes();
    }

}
