package com.vh.model;

import com.vh.model.enums.DepartmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DepartmentBudget {
    private DepartmentType departmentType;
    private Long money;
}