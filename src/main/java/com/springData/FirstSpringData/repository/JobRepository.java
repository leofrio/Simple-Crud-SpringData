package com.springData.FirstSpringData.repository;

import com.springData.FirstSpringData.orm.Job;
import com.springData.FirstSpringData.orm.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job,Integer> {
    List<Job> findByName(String name);
}
