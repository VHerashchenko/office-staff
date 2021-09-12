package com.vh.service.impl;

import com.vh.model.DepartmentBudget;
import com.vh.model.Manager;
import com.vh.model.Report;
import com.vh.model.Worker;
import com.vh.model.enums.RoleType;
import com.vh.service.CalculateSalaryService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class CalculateSalary implements CalculateSalaryService {

    @Override
    public DepartmentBudget setDefaultSalaryForDepartment(List<Worker> workers, DepartmentBudget department, double coefficient) {
        Integer defaultSalarySum = 0;

        for (Worker worker: workers){
            if (worker.getDepartment().equals(department.getDepartmentType())){
                defaultSalarySum += worker.getSalary();
            }
        }

        department.setMoney( (int) (defaultSalarySum * coefficient));

        return department;
    }

    @Override
    public List<Report> calculateSalaryByDepartment(List<Worker> workers, DepartmentBudget department) {
        List<Report> outputList = new ArrayList<>();
        int amountOfSubordinates = 0;

        for (Worker worker: workers){
            if (worker.getDepartment().equals(department.getDepartmentType())){
                if (worker.getRole().equals(RoleType.MANAGER)){
                    Manager manager = (Manager) worker;
                    amountOfSubordinates = manager.getSubordinateEmployee().size();
                }
                outputList.add(new Report(
                        worker.getName(),
                        worker.getBirthday(),
                        worker.getDepartment(),
                        worker.getRole(),
                        worker.getSalary(),
                        Date.from(Instant.now()),
                        amountOfSubordinates));
            }
            amountOfSubordinates = 0;
        }

        calculatePrizeForManagersSubordinate(outputList);

        calculateHalves(outputList, department.getMoney());


        return outputList;
    }
    private List<Report> calculatePrizeForManagersSubordinate(List<Report> reports){
        for (Report report : reports){
            if(report.getAmountOfSubordinate() != 0){
                report.setPrize(report.getPrize()
                                + (int) (report.getDefaultSalary() * Report.DEFAULT_PRIZE_FOR_SUBORDINATE)
                                * report.getAmountOfSubordinate());
            }
        }
        return reports;
    }

    protected abstract List<Report> calculateHalves(List<Report> reports, Integer budget);
}
