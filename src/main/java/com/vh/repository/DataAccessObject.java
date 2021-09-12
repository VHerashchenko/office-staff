package com.vh.repository;

import com.vh.model.Worker;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessObject {

    Worker getById(Integer id) throws SQLException;

    Worker create(Worker worker) throws SQLException;

    Worker update(Worker worker) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    List<Worker> findAll() throws SQLException;
}
