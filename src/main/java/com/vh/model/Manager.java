package com.vh.model;

import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter

@NoArgsConstructor
public class Manager extends Worker {
    private List<Worker> subordinateEmployee;

    public Manager (Integer id, String name, Date birthday, Date startDate, Integer salary, RoleType roleType, DepartmentType departmentType){
        super(id, name, birthday, startDate, salary, roleType, departmentType);
    }

    public Manager (Worker worker){
        super(worker.getId(), worker.getName(), worker.getBirthday(), worker.getStartDate(), worker.getSalary(), worker.getRole(), worker.getDepartment());
    }

    public void addSubordinateEmployee(Worker worker){
        if(subordinateEmployee == null)
            subordinateEmployee = new ArrayList<>();

        subordinateEmployee.add(worker);
    }

    public void removeSubordinateEmployeeByWorker(Worker worker){
        if(subordinateEmployee != null) {
            int index = -1;
            for (int i = 0; i < subordinateEmployee.size(); ++i) {
                if (subordinateEmployee.get(i).getId().equals(worker.getId())) {
                    index = i;
                }
            }
            if(index != -1){
                subordinateEmployee.remove(index);
            }
        }
    }
}