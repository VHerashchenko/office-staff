package com.vh.controller;

import com.vh.model.connection.ConnectionProperties;
import com.vh.repository.DataAccessObject;
import com.vh.repository.impl.WorkerDAO;

import java.sql.SQLException;

public class WorkerController {
    ConnectionProperties connectionProperties = new ConnectionProperties();
    DataAccessObject workerDAO = new WorkerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());

    public void processUser(){
        try {
            workerDAO.findAll().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}