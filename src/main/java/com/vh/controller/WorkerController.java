package com.vh.controller;

import com.vh.repository.DataAccessObject;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.repository.impl.ManagerDAO;
import com.vh.repository.impl.WorkerDAO;

import java.sql.SQLException;

public class WorkerController {
    ConnectionProperties connectionProperties = new ConnectionProperties();
    DataAccessObject workerDAO = new WorkerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());
    DataAccessObject managerDAO = new ManagerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());
    DataAccessObject otherStaffDAO = new ManagerDAO(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());

    public void processUser(){
        try {
            workerDAO.findAll().forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getRole()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            managerDAO.findAll().forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getRole()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            otherStaffDAO.findAll().forEach(x -> System.out.println(x.getId() + " " + x.getName() + " " + x.getRole()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}