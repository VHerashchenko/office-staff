package com.vh.service;

import com.vh.model.Worker;

import java.util.List;

public interface CrudServiceInterface {

    void create(Worker worker);

    void update(Worker worker);

    void delete(Worker worker);

    Worker getById(Integer id);

    List<Worker> findAll();
}