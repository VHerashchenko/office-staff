package com.vh.service.impl;

import com.vh.model.Worker;
import com.vh.repository.DataAccessObject;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.repository.impl.WorkerDAO;
import com.vh.service.CrudServiceInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerService implements CrudServiceInterface {

    private final DataAccessObject workerDAO;

    public WorkerService(ConnectionProperties connectionProperties){

        workerDAO = new WorkerDAO(connectionProperties.getUrl(), connectionProperties.getUser(),
                connectionProperties.getPassword());
    }

    @Override
    public Worker create(Worker worker){
        try {
            worker = workerDAO.create(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public Worker update(Worker worker){
        try {
            worker = workerDAO.update(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public void delete(Worker worker){
        try {
            workerDAO.deleteById(worker.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker getById(Integer id){
        Worker worker = new Worker();
        try {
            worker = workerDAO.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public List<Worker> findAll(){
        List<Worker> workers = new ArrayList<>();
        try {
            workers = workerDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }
}