package com.vh.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Worker {
    Integer workerId;
    String name;
    Date birthday;
    Date startDate;
    Integer salary;
    RoleType role;
}