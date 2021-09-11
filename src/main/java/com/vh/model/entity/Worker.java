package com.vh.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    Integer workerId;
    String name;
    Date birthday;
    Date startDate;
    Integer salary;
    RoleType role;
}