package com.vh.controller;

import com.vh.exceptions.ManagerStillHasSubordinateException;
import com.vh.model.DepartmentBudget;
import com.vh.model.Manager;
import com.vh.model.OtherStaff;
import com.vh.model.Worker;
import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;
import com.vh.repository.connection.ConnectionProperties;
import com.vh.service.CalculateSalaryService;
import com.vh.service.CrudServiceInterface;
import com.vh.service.impl.*;

import java.time.Instant;
import java.util.Date;
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
        //multiply with coefficient to autoSet departmentBudget. It is sum of default salary all department's workers ( * coefficient)
        double coefficient = 1.2;

        DepartmentBudget departmentBudgetAccounting = new DepartmentBudget();
        departmentBudgetAccounting.setDepartmentType(DepartmentType.ACCOUNTING);

        DepartmentBudget departmentBudgetCommercial = new DepartmentBudget();
        departmentBudgetCommercial.setDepartmentType(DepartmentType.COMMERCIAL);

        DepartmentBudget departmentBudgetManagerial = new DepartmentBudget();
        departmentBudgetManagerial.setDepartmentType(DepartmentType.MANAGERIAL);

        calculateSalary = new CalculateSalaryWithSameHalves(); //same halves

        List<Worker> workers = workerService.findAll(); //find all RoleType.WORKER
        List<Worker> managers = managerService.findAll(); //find all RoleType.MANAGER
        List<Worker> otherStaffs = otherStaffService.findAll(); //find all RoleType.OTHER

        List<Worker> allInOneList = Stream.of(
                        workers.stream(),
                        managers.stream(),
                        otherStaffs.stream())
                .flatMap(i -> i)
                .collect(Collectors.toList());

        System.out.println("======ACCOUNTING DEPARTMENT REPORTS (SAME HALVES)======\n");

        //calculate salary for Accounting department
        calculateSalary.setDefaultSalaryForDepartment(allInOneList, departmentBudgetAccounting, coefficient);
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetAccounting)
                .forEach(System.out::println);

        System.out.println("\n======COMMERCIAL DEPARTMENT REPORTS (SAME HALVES)======\n");

        //calculate salary for Commercial department
        calculateSalary.setDefaultSalaryForDepartment(allInOneList, departmentBudgetCommercial, coefficient);
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetCommercial)
                .forEach(System.out::println);

        System.out.println("\n======MANAGERIAL DEPARTMENT REPORTS (SAME HALVES)======\n");

        //calculate salary for Managerial department + set default budget for every department
        calculateSalary.setDefaultSalaryForDepartment(allInOneList, departmentBudgetManagerial, coefficient);
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetManagerial)
                .forEach(System.out::println);


        System.out.println("======ACCOUNTING DEPARTMENT REPORTS (PERCENT)======\n");

        calculateSalary = new CalculateSalaryWithPercentHalves(); //percent halves

        //calculate salary for Accounting department
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetAccounting)
                .forEach(System.out::println);

        System.out.println("\n======COMMERCIAL DEPARTMENT REPORTS (PERCENT)======\n");

        //calculate salary for Commercial department
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetCommercial)
                .forEach(System.out::println);

        System.out.println("\n======MANAGERIAL DEPARTMENT REPORTS (PERCENT)======\n");

        //calculate salary for Managerial department
        calculateSalary.calculateSalaryByDepartment(allInOneList, departmentBudgetManagerial)
                .forEach(System.out::println);

        System.out.println("\n======CHANGE ROLE WORKER WITH ID 2 TO MANAGER======\n");

        //change role from worker to manager
        Worker worker = workerService.getById(2);
        Manager manager = (Manager) workerService.changeRole(worker, RoleType.MANAGER);
        manager = (Manager) managerService.update(manager);

        managerService.findAll()
                .forEach(System.out::println);

        System.out.println("\n======ADD SUBORDINATE WORKER TO NEW MANAGER======\n");

        //add subordinate worker to new manager. This action reset worker with id 3 from his manager to new one
        manager.addSubordinateEmployee(workerService.getById(3));
        manager = (Manager) managerService.update(manager);

        managerService.findAll()
                .forEach(System.out::println);

        System.out.println("\n======TRY TO CHANGE ROLE MANAGER WITH SUBORDINATES======\n");

        //try to change role manager with subordinate workers (catch exception)
        try{
            managerService.changeRole(manager, RoleType.OTHER);
        } catch (ManagerStillHasSubordinateException e){
            System.out.println("Exception: manager has subordinate workers");
        }

        System.out.println("\n======REMOVE SUBORDINATE FROM MANAGER======\n");

        //remove subordinate worker from manager
        manager.removeSubordinateEmployeeByWorker(workerService.getById(3));
        manager = (Manager) managerService.update(manager);

        managerService.findAll()
                .forEach(System.out::println);

        System.out.println("\n======CHANGE ROLE MANAGER TO OTHER======\n");

        //changing role from manager to other without subordinates
        OtherStaff otherStaff = (OtherStaff) managerService.changeRole(manager, RoleType.OTHER);
        //set description to new RoleType.OTHER worker. without it would be default description
        otherStaff.setDescription("New Description");
        otherStaff = (OtherStaff) otherStaffService.update(otherStaff);

        System.out.println(otherStaff);

        System.out.println("\n======CREATE NEW MANAGER======\n");

        manager = new Manager();
        manager.setName("New Manager");
        manager.setBirthday(Date.from(Instant.now()));
        manager.setStartDate(Date.from(Instant.now()));
        manager.setRole(RoleType.MANAGER);
        manager.setSalary(2100000L);
        manager.setDepartment(DepartmentType.COMMERCIAL);

        manager = (Manager) managerService.create(manager);

        managerService.findAll()
                .forEach(System.out::println);

        System.out.println("\n======DELETE MANAGER======\n");

        managerService.delete(manager);

        managerService.findAll()
                .forEach(System.out::println);

    }
}