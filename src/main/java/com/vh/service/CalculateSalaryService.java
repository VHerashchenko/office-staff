package com.vh.service;

import com.vh.model.DepartmentBudget;
import com.vh.model.Report;
import com.vh.model.Worker;

import java.util.List;

public interface CalculateSalaryService {

    DepartmentBudget setDefaultSalaryForDepartment(List<Worker> workers, DepartmentBudget department, double coefficient);

    List<Report> calculateSalaryByDepartment(List<Worker> workers, DepartmentBudget department);
}
