package com.vh.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Manager extends Worker {
    List<Worker> subordinateEmployee;
}
