package com.vh.model;

import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class Report {

    public static double DEFAULT_PRIZE_FOR_BIRTHDAY = 0.1;
    public static double DEFAULT_PRIZE_FOR_SUBORDINATE = 0.05;

    private String name;
    private Date birthday;
    private DepartmentType departmentType;
    private RoleType roleType;
    private Integer amountOfSubordinate;
    private Long defaultSalary;
    private Long prize;
    private Long calculatedHalf;
    private Long sumAllPrizeSalary;
    private Date date;

    public Report(String name, Date birthday, DepartmentType departmentType, RoleType roleType, Long defaultSalary, Date date, Integer amountOfSubordinate){
        this.name = name;
        this.birthday = birthday;
        this.departmentType = departmentType;
        this.roleType = roleType;
        this.amountOfSubordinate = amountOfSubordinate;
        this.defaultSalary = defaultSalary;
        this.date = date;

        if(birthday.getMonth() == date.getMonth()){
            prize = (long) (defaultSalary * Report.DEFAULT_PRIZE_FOR_BIRTHDAY);
        }
        else {
            prize = 0L;
        }
    }

    public void calculateSum(){
        this.sumAllPrizeSalary = this.defaultSalary + this.prize + this.calculatedHalf;
    }

    public String toString(){
        return "Name: " + this.name + "\n"
                + "Birthday: " + this.birthday + "\n"
                + "Department: " + this.departmentType + "\n"
                + "Role: " + this.roleType + "\n"
                + "AmountOfSubordinate: " + this.amountOfSubordinate + "\n"
                + "DefaultSalary: " + this.defaultSalary + "\n"
                + "Prize: " + this.prize + "\n"
                + "CalculatedHalf: " + this.calculatedHalf + "\n"
                + "SumAllPrizeSalary: " + this.sumAllPrizeSalary + "\n"
                + "Date: " + this.date + "\n";

    }
}