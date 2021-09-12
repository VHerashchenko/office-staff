package com.vh.controller;

import com.vh.model.DepartmentBudget;
import com.vh.model.Worker;
import com.vh.model.enums.DepartmentType;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.service.CalculateSalaryService;
import com.vh.service.CrudServiceInterface;
import com.vh.service.impl.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppController {
    ConnectionProperties connectionProperties = new ConnectionProperties();
    CrudServiceInterface workerService = new WorkerService(connectionProperties);
    CrudServiceInterface managerService = new ManagerService(connectionProperties);
    CrudServiceInterface otherStaffService = new OtherStaffService(connectionProperties);
    CalculateSalaryService calculateSalary;

    public void processUser(){
        DepartmentBudget departmentBudgetAccounting = new DepartmentBudget();
        departmentBudgetAccounting.setDepartmentType(DepartmentType.ACCOUNTING);

//        calculateSalary = new CalculateSalaryWithSameHalves();
        calculateSalary = new CalculatedSalaryWithPercentHalf();

        List<Worker> workers = workerService.findAll();
        List<Worker> managers = managerService.findAll();
        List<Worker> otherStaffs = otherStaffService.findAll();

        List<Worker> allInOneList = Stream.of(
                        workers.stream(),
                        managers.stream(),
                        otherStaffs.stream())
                .flatMap(i -> i)
                .collect(Collectors.toList());

        calculateSalary.setDefaultSalaryForDepartment(allInOneList, departmentBudgetAccounting, 1.2);
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetAccounting).forEach(System.out::println);
    }
}