package com.springData.FirstSpringData.repository;

import com.springData.FirstSpringData.orm.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job,Integer> {

}
