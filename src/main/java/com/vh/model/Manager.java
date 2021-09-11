package com.vh.model;

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

    public Manager (Integer id, String name, Date birthday, Date startDate, Integer salary, RoleType roleType){
        super(id, name, birthday, startDate, salary, roleType);
    }

    public void addSubordinateEmployee(Worker worker){
        if(subordinateEmployee == null)
            subordinateEmployee = new ArrayList<>();

        subordinateEmployee.add(worker);
    }

    public void removeSubordinateEmployee(Worker worker){
        if(subordinateEmployee != null)
            subordinateEmployee.remove(worker);
    }
}