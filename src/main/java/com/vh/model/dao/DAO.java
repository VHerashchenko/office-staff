package com.vh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {

    protected Connection myConn;

    public DAO(String jdbc, String user, String password){
        openConnection(jdbc, user, password);
    }

    public void openConnection(String jdbc, String user, String password) {
        try {
            myConn = DriverManager.getConnection(jdbc, user, password);
            Thread printingHook = new Thread(this::close);
            Runtime.getRuntime().addShutdownHook(printingHook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            myConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}