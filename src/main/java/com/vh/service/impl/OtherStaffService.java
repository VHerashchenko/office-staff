package com.vh.service.impl;

import com.vh.model.Manager;
import com.vh.model.Worker;
import com.vh.model.enums.RoleType;
import com.vh.repository.DataAccessObject;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.repository.impl.OtherStaffDAO;
import com.vh.service.CrudServiceInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtherStaffService implements CrudServiceInterface {

    private final DataAccessObject otherStaffDAO;

    public OtherStaffService(ConnectionProperties connectionProperties){

        otherStaffDAO = new OtherStaffDAO(connectionProperties.getUrl(), connectionProperties.getUser(),
                connectionProperties.getPassword());
    }

    @Override
    public Worker create(Worker worker){
        try {
            worker = otherStaffDAO.create(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public Worker update(Worker worker){
        try {
            worker = otherStaffDAO.update(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public void delete(Worker otherStaff){
        try {
            otherStaffDAO.deleteById(otherStaff.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker getById(Integer id){
        Worker worker = new Worker();
        try {
            worker = otherStaffDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public List<Worker> findAll(){
        List<Worker> workers = new ArrayList<>();
        try {
            workers = otherStaffDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    @Override
    public Worker changeRole(Worker manager, RoleType roleType) {
        if(roleType.equals(RoleType.WORKER)){
            manager.setRole(RoleType.WORKER);
            return new Worker(manager.getId(), manager.getName(), manager.getBirthday(),
                    manager.getStartDate(), manager.getSalary(), manager.getRole(), manager.getDepartment());
        }
        else if(roleType.equals(RoleType.MANAGER)){
            manager.setRole(RoleType.MANAGER);
            return new Manager(manager.getId(), manager.getName(), manager.getBirthday(),
                    manager.getStartDate(), manager.getSalary(), manager.getRole(), manager.getDepartment());
        }
        else{
            return manager;
        }
    }
}
