package com.vh.model;

import com.vh.model.enums.RoleType;
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
    private Integer id;
    private String name;
    private Date birthday;
    private Date startDate;
    private Integer salary;
    private RoleType role;
}