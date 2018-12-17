package com.janu.location.Repositories;

import com.janu.location.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface LocationRepository extends JpaRepository<Location, Integer> {
}
