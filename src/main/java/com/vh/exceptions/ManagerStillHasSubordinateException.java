package com.vh.exceptions;

import com.vh.model.Worker;

public class ManagerStillHasSubordinateException extends RuntimeException {

    public ManagerStillHasSubordinateException (Worker worker){
        super("Manager with id = " + worker.getId() + "and Name = " + worker.getName());
    }
}
