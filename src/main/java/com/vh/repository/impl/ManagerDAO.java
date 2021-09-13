package com.vh.repository.impl;

import com.vh.model.Manager;
import com.vh.model.Worker;
import com.vh.model.enums.DepartmentType;
import com.vh.model.enums.RoleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO extends WorkerDAO {

    private final RoleType roleType = RoleType.MANAGER;

    public ManagerDAO(String jdbc, String user, String password) {
        super(jdbc, user, password);
    }

    @Override
    public Manager getById(Integer id) throws SQLException {
        Manager manager = new Manager(super.getById(id));
        List<Worker> subordinateEmployee = new ArrayList<>();

        ResultSet resultSet;
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("SELECT * FROM vh_worker " +
                "WHERE manager_id = ?");
        myStmt.setLong(1, id);

        resultSet = myStmt.executeQuery();

        while (resultSet.next()){
            subordinateEmployee.add(
                    new Worker(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("birthday"),
                            resultSet.getDate("start_date"),
                            resultSet.getInt("salary"),
                            RoleType.valueOf(resultSet.getString("role")),
                            DepartmentType.valueOf(resultSet.getString("department"))));
        }

        manager.setSubordinateEmployee(subordinateEmployee);

        return manager;
    }

    @Override
    public Manager create(Worker worker) throws SQLException {
        Manager manager = (Manager) super.create(worker);

        addManagerToAllConnectedWorker(manager);

        return manager;
    }

    @Override
    public Manager update(Worker worker) throws SQLException {
        Manager manager = (Manager) super.update(worker);

        deleteManagerFromAllWorker(manager);
        addManagerToAllConnectedWorker(manager);

        return manager;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        Manager manager = new Manager();
        manager.setId(id);

        deleteManagerFromAllWorker(manager);
        super.deleteById(id);
    }

    @Override
    public List<Worker> findAll() throws SQLException {
        List<Manager> managers = new ArrayList<>();

        PreparedStatement myStmt;
        ResultSet resultSet;

        myStmt = myConn.prepareStatement("SELECT * FROM vh_worker WHERE role = ?");
        myStmt.setObject(1, roleType, Types.OTHER);
        resultSet = myStmt.executeQuery();

        while(resultSet.next()){
            managers.add(
                    new Manager(resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getDate("birthday"),
                                resultSet.getDate("start_date"),
                                resultSet.getInt("salary"),
                                RoleType.valueOf(resultSet.getString("role")),
                                DepartmentType.valueOf(resultSet.getString("department"))));
        }

        for (Manager manager : managers){
            addAllConnectedWorkers(manager);
        }

        return new ArrayList<>(managers);
    }

    private void addAllConnectedWorkers(Manager manager) throws SQLException {
        PreparedStatement myStmt;
        ResultSet resultSet;

        myStmt = myConn.prepareStatement("SELECT * FROM vh_worker WHERE manager_id = ?");
        myStmt.setLong(1, manager.getId());

        resultSet = myStmt.executeQuery();

        manager.setSubordinateEmployee(new ArrayList<>());

        while (resultSet.next()){
            manager.getSubordinateEmployee().add(
                    new Worker(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("birthday"),
                            resultSet.getDate("start_date"),
                            resultSet.getInt("salary"),
                            RoleType.valueOf(resultSet.getString("role")),
                            DepartmentType.valueOf(resultSet.getString("department"))));
        }
    }

    private void deleteManagerFromAllWorker(Manager manager) throws SQLException {
        PreparedStatement myStmt;

        myStmt = myConn.prepareStatement("UPDATE vh_worker SET\n" +
                    "manager_id = null\n" +
                    "WHERE manager_id = ?");
        myStmt.setLong(1, manager.getId());

        myStmt.execute();
    }

    private void addManagerToAllConnectedWorker(Manager manager) throws SQLException {
        if(manager.getSubordinateEmployee() == null) return;
        PreparedStatement myStmt;

        for (Worker subEmp : manager.getSubordinateEmployee()){
            myStmt = myConn.prepareStatement("UPDATE vh_worker SET\n" +
                        "manager_id = ?\n" +
                        "WHERE id = ?");
            myStmt.setLong(1, manager.getId());
            myStmt.setLong(2, subEmp.getId());

            myStmt.execute();
        }
    }
}