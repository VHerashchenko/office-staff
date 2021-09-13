package com.vh.model;

import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class OtherStaff extends Worker {
    private String description = "default description";

    public OtherStaff (Integer id, String name, Date birthday, Date startDate, Integer salary, RoleType roleType, DepartmentType departmentType, String description){
        super(id, name, birthday, startDate, salary, roleType, departmentType);
        if(description != null){
            this.description = description;
        }
    }
}