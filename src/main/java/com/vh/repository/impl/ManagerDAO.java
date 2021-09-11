package com.vh.repository.impl;

import com.vh.model.entity.Manager;
import com.vh.model.entity.Worker;

import java.sql.SQLException;
import java.util.List;

public class ManagerDAO extends WorkerDAO {
    public ManagerDAO(String jdbc, String user, String password) {
        super(jdbc, user, password);
    }

    @Override
    public Manager getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Manager create(Worker manager) throws SQLException {
        return null;
    }

    @Override
    public Manager update(Worker manager) throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public List<Worker> findAll() throws SQLException {
        return null;
    }
}