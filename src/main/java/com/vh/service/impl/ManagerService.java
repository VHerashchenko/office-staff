package com.vh.service.impl;

import com.vh.model.Worker;
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
        Worker worker = new Worker();
        try {
            worker = managerDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
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
}
