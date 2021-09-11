package com.vh.controller;

import com.vh.model.connection.ConnectionProperties;
import com.vh.model.dao.WorkerDAO;
import com.vh.model.entity.Worker;

import java.sql.SQLException;

public class WorkerController {
    ConnectionProperties connectionProperties = new ConnectionProperties();
    WorkerDAO workerDAO = new WorkerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());
    Worker worker;

    public void processUser(){
        try {
            worker = workerDAO.getById(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(worker.getWorkerId());
        System.out.println(worker.getName());
        System.out.println(worker.getBirthday());
        System.out.println(worker.getStartDate());
        System.out.println(worker.getSalary());
        System.out.println(worker.getRole());

    }
}