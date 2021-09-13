package com.vh.service.impl;

import com.vh.model.Report;

import java.util.List;

public class CalculateSalaryWithSameHalves extends CalculateSalary{

    @Override
    protected List<Report> calculateHalves(List<Report> reports, Long budget) {
        long allMoneyInProgress = 0;
        long halfForEach;

        for (Report report : reports){
            allMoneyInProgress = allMoneyInProgress + report.getDefaultSalary() + report.getPrize();
        }

        halfForEach = (budget - allMoneyInProgress) / reports.size();

        for (Report report : reports){
            report.setCalculatedHalf(halfForEach);
            report.calculateSum();
        }
        return reports;
    }
}
