package com.vh.service.impl;

import com.vh.model.Report;

import java.util.List;

public class CalculateSalaryWithSameHalves extends CalculateSalary{

    @Override
    protected List<Report> calculateHalves(List<Report> reports, Integer budget) {
        int allMoneyInProgress = 0;
        int halfForEach;

        for (Report report : reports){
            allMoneyInProgress = report.getDefaultSalary() + report.getPrize();
        }

        halfForEach = (budget - allMoneyInProgress) / reports.size();

        for (Report report : reports){
            report.setCalculatedHalf(halfForEach);
            report.calculateSum();
        }
        return reports;
    }
}
