package com.springData.FirstSpringData.repository;

import com.springData.FirstSpringData.orm.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker,Integer> {
    List<Worker> findByName(String name);
}
