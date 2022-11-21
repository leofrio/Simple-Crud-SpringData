package com.springData.FirstSpringData.repository;

import com.springData.FirstSpringData.orm.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WorkPlaceRepository extends JpaRepository<WorkPlace,Integer> {
}
