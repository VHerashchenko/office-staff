package com.vh.service.impl;

import com.vh.exceptions.ManagerStillHasSubordinateException;
import com.vh.model.Manager;
import com.vh.model.OtherStaff;
import com.vh.model.Worker;
import com.vh.model.enums.RoleType;
import com.vh.repository.DataAccessObject;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.repository.impl.ManagerDAO;
import com.vh.service.CrudServiceInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerService implements CrudServiceInterface {

    private final DataAccessObject managerDAO;

    public ManagerService(ConnectionProperties connectionProperties){

        managerDAO = new ManagerDAO(connectionProperties.getUrl(), connectionProperties.getUser(),
                connectionProperties.getPassword());
    }

    @Override
    public Worker create(Worker worker){
        try {
            worker = managerDAO.create(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public Worker update(Worker worker){
        try {
            worker = managerDAO.update(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public void delete(Worker worker){
        try {
            managerDAO.deleteById(worker.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker getById(Integer id){
        Manager manager = new Manager();
        try {
            manager = (Manager) managerDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    @Override
    public List<Worker> findAll(){
        List<Worker> workers = new ArrayList<>();
        try {
            workers = managerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    @Override
    public Worker changeRole(Worker worker, RoleType roleType) throws ManagerStillHasSubordinateException {
        Manager manager = (Manager) worker;
            if(manager.getSubordinateEmployee().size() > 0) throw new ManagerStillHasSubordinateException(manager);

        if(roleType.equals(RoleType.WORKER)){
            manager.setRole(RoleType.WORKER);
            return new Worker(manager.getId(), manager.getName(), manager.getBirthday(),
                    manager.getStartDate(), manager.getSalary(), manager.getRole(), manager.getDepartment());
        }
        else if(roleType.equals(RoleType.OTHER)){
            manager.setRole(RoleType.OTHER);
            return new OtherStaff(manager.getId(), manager.getName(), manager.getBirthday(),
                    manager.getStartDate(), manager.getSalary(), manager.getRole(), manager.getDepartment(), null);
        }
        else{
            return manager;
        }
    }
}
