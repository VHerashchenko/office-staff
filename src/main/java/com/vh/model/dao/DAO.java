package com.vh.model.dao;

import com.vh.model.ConnectionConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class DAO {

    protected Connection myConn;


    public void openConnection() {
        try {
            myConn = DriverManager.getConnection(ConnectionConstant.JDBC_URL, ConnectionConstant.USERNAME, ConnectionConstant.PASSWORD);
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

    public void deleteById(Integer id) throws SQLException {
        PreparedStatement myStmt;

        openConnection();

        myStmt = myConn.prepareStatement("DELETE FROM vh_role_worker " +
                "WHERE worker_id = ?");
        myStmt.setLong(1, id);

        myStmt.execute();

        myStmt = myConn.prepareStatement("DELETE FROM vh_worker " +
                "WHERE id = ?");
        myStmt.setLong(1, id);

        myStmt.execute();

        close();
    }
}