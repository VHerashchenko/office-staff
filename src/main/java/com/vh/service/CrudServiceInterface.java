package com.vh.service;

import com.vh.model.Worker;

import java.util.List;

public interface CrudServiceInterface {

    Worker create(Worker worker);

    Worker update(Worker worker);

    void delete(Worker worker);

    Worker getById(Integer id);

    List<Worker> findAll();
}