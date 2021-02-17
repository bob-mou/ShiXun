package com.example.demo.Serviceimpl;

import com.example.demo.Entity.Manager;
import com.example.demo.Mapper.ManagerMapper;
import com.example.demo.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ManagerServiceimpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;


    @Override
    public Manager ManagerLogin(Manager manager) {
        return managerMapper.ManagerLogin(manager);
    }

    @Override
    public List<Manager> managerFile() {
        return managerMapper.managerFile();
    }

    @Override
    public Integer deleteManagerByID(Integer mid) {
        return managerMapper.deleteManagerByID(mid);
    }

    @Override
    public Integer insertManager(Manager manager) {
        return managerMapper.insertManager(manager);
    }

    @Override
    public Manager findManagerByID(Integer mid) {
        return managerMapper.findManagerByID(mid);
    }

    @Override
    public List<Manager> findManagerByAccount(String manager_account) {
        return managerMapper.findManagerByAccount(manager_account);
    }

    @Override
    public List<Manager> findManagerByDate(Date manager_date) {
        return managerMapper.findManagerByDate(manager_date);
    }

    @Override
    public List<Manager> findManagerByName(String manager_name){
        return managerMapper.findManagerByName(manager_name);
    }

    @Override
    public Integer updateManager(Manager manager) {
        return managerMapper.updateManager(manager);
    }

    @Override
    public Integer countAllManagerNum() {
        return managerMapper.countAllManagerNum();
    }
}
