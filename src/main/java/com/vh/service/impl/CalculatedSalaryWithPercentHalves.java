package com.vh.service.impl;

import com.vh.model.Report;

import java.util.List;

public class CalculatedSalaryWithPercentHalves extends CalculateSalary {

    @Override
    protected List<Report> calculateHalves(List<Report> reports, Long budget) {
        long allMoneyInProgress = 0;
        long halfForEach;
        long percent;

        for (Report report : reports){
            allMoneyInProgress = report.getDefaultSalary() + report.getPrize();
        }

        for (Report report : reports){
            percent = report.getDefaultSalary() * 100 / allMoneyInProgress;

            halfForEach = (budget - allMoneyInProgress) * percent / 100;

            report.setCalculatedHalf(halfForEach);
            report.calculateSum();
        }
        return reports;
    }
}
