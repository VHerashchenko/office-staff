package com.vh.controller;

import com.vh.repository.connection.ConnectionProperties;
import com.vh.service.CrudServiceInterface;
import com.vh.service.impl.ManagerService;
import com.vh.service.impl.OtherStaffService;
import com.vh.service.impl.WorkerService;

public class AppController {
    ConnectionProperties connectionProperties = new ConnectionProperties();
    CrudServiceInterface workerService = new WorkerService(connectionProperties);
    CrudServiceInterface managerService = new ManagerService(connectionProperties);
    CrudServiceInterface otherStaffService = new OtherStaffService(connectionProperties);

    public void processUser(){

    }
}