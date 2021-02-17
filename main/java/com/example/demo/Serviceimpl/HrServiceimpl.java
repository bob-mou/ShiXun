package com.example.demo.Serviceimpl;

import com.example.demo.Entity.HRDate;
import com.example.demo.Entity.Hr;
import com.example.demo.Mapper.HrMapper;
import com.example.demo.Mapper.PingfenMapper;
import com.example.demo.Service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class HrServiceimpl implements HrService {
    @Autowired
    private HrMapper hrMapper;
    @Autowired
    private PingfenMapper pingfenMapper;
    @Override
    public Integer addHr(Hr hr) {
        return hrMapper.addHr(hr);
    }

    @Override
    public List<Hr> HrAccountCheck(String hr_account) {
        return hrMapper.HrAccountCheck(hr_account);
    }

    @Override
    public Hr hrLogin(String hr_account, String hr_password) {
        return hrMapper.hrLogin(hr_account,hr_password);
    }

    @Override
    public List<Hr> findAllHR() {
        return hrMapper.findAllHR();
    }

    @Override
    public List<Hr> findAllIsHR(){
        return hrMapper.findAllIsHR();
    }

    @Override
    public List<Hr> findHrByAccount(String hr_account) {
        return hrMapper.findHrByAccount(hr_account);
    }

    @Override
    public List<Hr> findHrByName(String hr_name){
        List<Hr> hr2= hrMapper.findHrByName(hr_name);
        List<Hr> hr=pingfenMapper.allavgHR();
        for(int i=0;i<hr2.size();i++){
            int hid=hr2.get(i).getHid();
            aa:for(int j=0;j<hr.size();j++){
                if(hid==hr.get(j).getHid()){
                    hr2.get(i).setAvgHR(hr.get(j).getAvgHR());
                    break aa;
                }
            }
            if(hr2.get(i).getAvgHR()==null){
                hr2.get(i).setAvgHR(5);
            }
        }
        return hr2;
    }

    @Override
    public List<Hr> findHrByNameandAccount(String hr_name, String hr_account) {
        return hrMapper.findHrByNameandAccount(hr_name,hr_account);
    }

    @Override
    public Hr findAHRByID(Integer hid) {
        return hrMapper.findAHRByID(hid);
    }

    @Override
    public Integer applyRe(Integer id) {
        return hrMapper.applyRe(id);
    }

    @Override
    public Integer updateMessage(Hr hr) {
        return hrMapper.updateMessage(hr);
    }

    @Override
    public Integer update(Hr hr) {
        return hrMapper.update(hr);
    }

    @Override
    public Integer countHr() {
        return hrMapper.countHr();
    }

    @Override
    public List<HRDate> findHRDate() {
        return hrMapper.findHRDate();
    }

    @Override
    public List<Hr> findAllHRAndPF() {
        return hrMapper.findAllHRAndPF();
    }

    //HR推荐的实现
    @Override
    public List<Hr> findHR() {
        List<Hr> hr2 =hrMapper.findHR();
        List<Hr> hr=pingfenMapper.allavgHR();
        for(int i=0;i<hr2.size();i++){
            int hid=hr2.get(i).getHid();
            aa:for(int j=0;j<hr.size();j++){
                if(hid==hr.get(j).getHid()){
                    hr2.get(i).setAvgHR(hr.get(j).getAvgHR());
                    break aa;
                }
            }
            if(hr2.get(i).getAvgHR()==null){
                hr2.get(i).setAvgHR(5);
            }
        }
        return hr2;
    }
}
