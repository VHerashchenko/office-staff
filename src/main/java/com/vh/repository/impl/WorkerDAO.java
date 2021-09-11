package com.vh.repository.impl;

import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;
import com.vh.model.Worker;
import com.vh.repository.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAO implements DataAccessObject {
    private final RoleType roleType = RoleType.WORKER;

    protected Connection myConn;

    public WorkerDAO(String url, String user, String password){
        openConnection(url, user, password);
    }

    @Override
    public Worker getById(Integer id) throws SQLException {
        Worker worker = new Worker();

        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("select * from vh_worker w \n" +
                "where w.id = ?");
        myStmt.setLong(1, id);

        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            worker.setId(resultSet.getInt("id"));
            worker.setName(resultSet.getString("name"));
            worker.setBirthday(resultSet.getDate("birthday"));
            worker.setStartDate(resultSet.getDate("start_date"));
            worker.setSalary(resultSet.getInt("salary"));
            worker.setRole(RoleType.valueOf(resultSet.getString("role")));
            worker.setDepartment(DepartmentType.valueOf(resultSet.getString("department")));
        }
        return worker;
    }

    @Override
    public Worker create(Worker worker) throws SQLException {
        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("INSERT INTO vh_worker (name, birthday, start_date, salary, role, department)" +
                "VALUES (? ,? ,? ,? ,?, ?) RETURNING id;");
        setAllProp(myStmt, worker);

        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            worker.setId(resultSet.getInt("id"));
        }

        return worker;
    }

    @Override
    public Worker update(Worker worker) throws SQLException {
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("UPDATE vh_worker SET " +
                "name = ?,"+
                "birthday = ?, " +
                "start_date = ?, " +
                "salary = ?, " +
                "role = ?, " +
                "department = ? " +
                "WHERE id = ?");
        setAllProp(myStmt, worker);

        myStmt.execute();

        return worker;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("DELETE FROM vh_worker " +
                "WHERE id = ?");
        myStmt.setLong(1, id);

        myStmt.execute();
    }

    @Override
    public List<Worker> findAll() throws SQLException {
        List<Worker> workers = new ArrayList<>();

        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("SELECT * FROM vh_worker WHERE role = ?");
        myStmt.setObject(1, roleType, Types.OTHER);
        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            workers.add(
                    new Worker(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("birthday"),
                            resultSet.getDate("start_date"),
                            resultSet.getInt("salary"),
                            RoleType.valueOf(resultSet.getString("role")),
                            DepartmentType.valueOf(resultSet.getString("department"))));
        }
        return workers;
    }

    private void setAllProp(PreparedStatement myStmt, Worker worker) throws SQLException {
        myStmt.setString(1, worker.getName());
        myStmt.setDate(2, (Date) worker.getBirthday());
        myStmt.setDate(3, (Date) worker.getStartDate());
        myStmt.setLong(4, worker.getSalary());
        myStmt.setObject(5, worker.getRole(), Types.OTHER);
        myStmt.setObject(6, worker.getDepartment(), Types.OTHER);
        myStmt.setLong(7, worker.getId());
    }

    private void openConnection(String jdbc, String user, String password) {
        try {
            myConn = DriverManager.getConnection(jdbc, user, password);
            Thread printingHook = new Thread(this::close);
            Runtime.getRuntime().addShutdownHook(printingHook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            myConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}