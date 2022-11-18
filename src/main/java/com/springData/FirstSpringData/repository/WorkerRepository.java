package com.springData.FirstSpringData.repository;

import com.springData.FirstSpringData.orm.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker,Integer> {
}
