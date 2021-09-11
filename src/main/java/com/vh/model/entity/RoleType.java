package com.vh.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleType {
    MANAGER (1, "MANAGER"),
    WORKER(2, "WORKER"),
    DIRECTOR(3, "DIRECTOR");

    private Integer id;
    private String name;

    @Override
    public String toString(){
        return name;
    }
}
