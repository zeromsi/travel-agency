package com.travelagency.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelagency.data.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long>{

}
