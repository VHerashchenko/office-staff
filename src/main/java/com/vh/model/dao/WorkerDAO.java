package com.vh.model.dao;

import com.vh.model.entity.RoleType;
import com.vh.model.entity.Worker;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO extends DAO{

    public Worker getByID(Integer id) throws SQLException {
        Worker worker = new Worker();

        ResultSet resultSet;
        PreparedStatement myStmt;

        openConnection();

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
            worker.setRoles(getAllRolesById(id));
        }

        close();
        return worker;
    }

    public Worker createWorker(Worker worker) throws SQLException {
        ResultSet resultSet;
        PreparedStatement myStmt;

        openConnection();

        myStmt = myConn.prepareStatement("INSERT INTO vh_worker (name, birthday, start_date, salary)" +
                "VALUES (?, ? , ? , ?)");
        myStmt.setString(1, worker.getName());
        myStmt.setDate(2, (Date) worker.getBirthday());
        myStmt.setDate(3, (Date) worker.getStartDate());
        myStmt.setLong(4, worker.getSalary());

        myStmt.execute();

        myStmt = myConn.prepareStatement("SELECT currval(pg_get_serial_sequence('vh_worker','id'))");
        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            worker.setWorkerId(resultSet.getInt("currval"));
        }

        insertAllRolesById(worker.getWorkerId(), worker.getRoles());

        close();
        return worker;
    }

    public Worker updateWorker(Worker worker) throws SQLException {
        PreparedStatement myStmt;

        openConnection();

        myStmt = myConn.prepareStatement("UPDATE vh_worker SET " +
                "name = ?,"+
                "birthday = ?, " +
                "start_date = ?, " +
                "salary = ? " +
                "WHERE id = ?");
        myStmt.setString(1, worker.getName());
        myStmt.setDate(2, (Date) worker.getBirthday());
        myStmt.setDate(3, (Date) worker.getStartDate());
        myStmt.setLong(4, worker.getSalary());
        myStmt.setLong(5, worker.getWorkerId());

        myStmt.execute();


        close();
        return worker;
    }

//    private Worker changeRoles(Worker worker) throws SQLException {
//
//        List<RoleType> currentRoles = getAllRolesById(worker.getWorkerId());
//
//        if (currentRoles.equals(worker.getRoles())) {
//
//        }
//
//
//    }

    private void insertAllRolesById(Integer id, List<RoleType> roleList) throws SQLException {
        PreparedStatement myStmt;
        for (RoleType roleType : roleList){
            myStmt = myConn.prepareStatement("INSERT INTO vh_role_worker (worker_id, role_id)" +
                    "VALUES (?, ?)");
            myStmt.setLong(1, id);
            myStmt.setInt(2, roleType.getId());

            myStmt.execute();
        }
    }


    private List<RoleType> getAllRolesById(Integer id) throws SQLException {
        ResultSet resultSet;
        PreparedStatement myStmt;

        List<RoleType> roles = new ArrayList<>();

        myStmt = myConn.prepareStatement("select * from vh_role r " +
                "JOIN vh_role_worker rw ON " +
                "r.id = rw.role_id " +
                "where worker_id = ?");
        myStmt.setLong(1, id);

        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            roles.add(RoleType.valueOf(resultSet.getString("name")));
        }
        return roles;
    }
}