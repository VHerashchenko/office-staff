package com.vh.controller;

import com.vh.model.Manager;
import com.vh.model.Worker;
import com.vh.repository.DataAccessObject;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.repository.impl.ManagerDAO;
import com.vh.repository.impl.WorkerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerController {
    ConnectionProperties connectionProperties = new ConnectionProperties();
    DataAccessObject workerDAO = new WorkerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());
    DataAccessObject managerDAO = new ManagerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());
    DataAccessObject otherStaffDAO = new ManagerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());

    public void processUser(){
        List<Worker> workers = new ArrayList<>();

        try {
            workers = workerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        workers.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getRole() + " " + x.getDepartment()));

        List<Worker> managers = new ArrayList<>();

        try {
            managers = managerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Manager manager = (Manager) managers.get(0);
        List<Worker> workerList = manager.getSubordinateEmployee();

        for(int i = 0; i < workers.size(); ++i){
            for(int j = 0; j < workerList.size(); ++j)
                if(workerList.get(j).getName().equals(workers.get(i).getName())){
                    manager.removeSubordinateEmployee(i);
                }
        }

        manager.getSubordinateEmployee().forEach(System.out::println);

        try {
            manager = (Manager) managerDAO.update(manager);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        managers.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getRole() + " " + x.getDepartment()));

        List<Worker> otherStaffs = new ArrayList<>();

        try {
            otherStaffs = otherStaffDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        otherStaffs.forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getRole() + " " + x.getDepartment()));
    }
}