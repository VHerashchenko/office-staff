package com.vh.service;

import com.vh.model.DepartmentBudget;
import com.vh.model.Report;
import com.vh.model.Worker;
import com.vh.model.enums.DepartmentType;

import java.util.List;

public interface CalculateSalaryService {

    DepartmentBudget setDefaultSalaryForDepartment(List<Worker> workers, DepartmentBudget department, double coefficient);

    List<Report> calculateSalaryByDepartment(List<Worker> workers, DepartmentBudget department);
}
