package com.example.demo.Serviceimpl;

import com.example.demo.Entity.Hr;
import com.example.demo.Entity.Pingfen;
import com.example.demo.Mapper.PingfenMapper;
import com.example.demo.Service.PinfenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PinfenServiceimpl implements PinfenService {
    @Autowired
    private PingfenMapper pingfenMapper;

    @Override
    public List<Pingfen> findrPinfen() {
        return pingfenMapper.findrPinfen();
    }

    @Override
    public List<Pingfen> findrPinfenByName(String name) {
        return pingfenMapper.findrPinfenByName(name);
    }

    @Override
    public List<Pingfen> findrPinfenByPfmark(Integer PF) {
        return pingfenMapper.findrPinfenByPfmark(PF);
    }

    @Override
    public List<Pingfen> findrPinfenByPfmarkandName(Pingfen pingfen) {
        return pingfenMapper.findrPinfenByPfmarkandName(pingfen); }

    @Override
    public List<Pingfen> findHRPinfen()
    {
        return pingfenMapper.findHRPinfen();
    }

    @Override
    public List<Pingfen> findHRPinfenByName(String hr_name)
    {
        return pingfenMapper.findHRPinfenByName(hr_name);
    }

    @Override
    public List<Pingfen> findHRPinfenByPfmark(Integer hrmark) {
        return pingfenMapper.findHRPinfenByPfmark(hrmark);
    }

    @Override
    public List<Pingfen> findHRPinfenByPfmarkandName(String hr_name, Integer hrmark) {
        return pingfenMapper.findHRPinfenByPfmarkandName(hr_name , hrmark);
    }

    @Override
    public Integer pingfenRe(Pingfen pingfen) {
        return pingfenMapper.pingfenRe(pingfen);
    }

    @Override
    public Integer pingfenHr(Pingfen pingfen) {
        return pingfenMapper.pingfenHr(pingfen);
    }

    @Override
    public Integer avgHR(Integer hid) {
        return pingfenMapper.avgHR(hid);
    }

}
