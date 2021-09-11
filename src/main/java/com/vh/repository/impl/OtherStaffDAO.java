package com.vh.repository.impl;

import com.vh.model.entity.OtherStaff;
import com.vh.model.entity.Worker;

import java.sql.SQLException;
import java.util.List;

public class OtherStaffDAO extends WorkerDAO{
    public OtherStaffDAO(String url, String user, String password) {
        super(url, user, password);
    }

    @Override
    public OtherStaff getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public OtherStaff create(Worker otherStaff) throws SQLException {
        return null;
    }

    @Override
    public OtherStaff update(Worker otherStaff) throws SQLException {
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
