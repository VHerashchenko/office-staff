package com.vh.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Worker {
    Integer workerId;
    String name;
    Date birthday;
    Date startDate;
    Integer salary;
    List<RoleType> roles;

    public void addRole(RoleType roleType){
        roles.add(roleType);
    }

    public void removeRole(RoleType roleType){
        roles.remove(roleType);
    }
}


