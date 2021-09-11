package com.vh.controller;

import com.vh.model.dao.WorkerDAO;
import com.vh.model.entity.RoleType;
import com.vh.model.entity.Worker;

import java.sql.SQLException;

public class WorkerController {
    WorkerDAO workerDAO = new WorkerDAO();
    Worker worker;

    public void processUser(){
        try {
            worker = workerDAO.getByID(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(worker.getWorkerId());
        System.out.println(worker.getName());
        System.out.println(worker.getBirthday());
        System.out.println(worker.getStartDate());
        System.out.println(worker.getSalary());
        worker.getRoles().forEach(x -> System.out.println(x.getName()));


        worker.addRole(RoleType.MANAGER);

        try {
            worker = workerDAO.createWorker(worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            workerDAO.deleteById(worker.getWorkerId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
