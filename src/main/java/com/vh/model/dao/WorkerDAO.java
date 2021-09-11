package com.vh.model.dao;

import com.vh.model.entity.RoleType;
import com.vh.model.entity.Worker;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerDAO extends DAO{

    public WorkerDAO(String url, String user, String password){
        super(url, user, password);
    }

    public Worker getById(Integer id) throws SQLException {
        Worker worker = new Worker();

        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("select * from vh_worker w \n" +
                "where w.id = ?");
        myStmt.setLong(1, id);

        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            worker.setWorkerId(resultSet.getInt("id"));
            worker.setName(resultSet.getString("name"));
            worker.setBirthday(resultSet.getDate("birthday"));
            worker.setStartDate(resultSet.getDate("start_date"));
            worker.setSalary(resultSet.getInt("salary"));
            worker.setRole(RoleType.valueOf(resultSet.getString("role")));
        }

        return worker;
    }

    public Worker create(Worker worker) throws SQLException {
        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("INSERT INTO vh_worker (name, birthday, start_date, salary, role)" +
                "VALUES (?, ? , ? , ?, ?)");
        myStmt.setString(1, worker.getName());
        myStmt.setDate(2, (Date) worker.getBirthday());
        myStmt.setDate(3, (Date) worker.getStartDate());
        myStmt.setLong(4, worker.getSalary());
        myStmt.setString(5, worker.getRole().toString());

        myStmt.execute();

        return worker;
    }

    public Worker update(Worker worker) throws SQLException {
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("UPDATE vh_worker SET " +
                "name = ?,"+
                "birthday = ?, " +
                "start_date = ?, " +
                "salary = ?, " +
                "role = ? " +
                "WHERE id = ?");
        myStmt.setString(1, worker.getName());
        myStmt.setDate(2, (Date) worker.getBirthday());
        myStmt.setDate(3, (Date) worker.getStartDate());
        myStmt.setLong(4, worker.getSalary());
        myStmt.setString(5, worker.getRole().toString());
        myStmt.setLong(6, worker.getWorkerId());

        myStmt.execute();

        return worker;
    }

    public void deleteById(Integer id) throws SQLException {
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("DELETE FROM vh_worker " +
                "WHERE id = ?");
        myStmt.setLong(1, id);

        myStmt.execute();
    }
}