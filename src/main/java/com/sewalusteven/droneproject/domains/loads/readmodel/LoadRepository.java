package com.sewalusteven.droneproject.domains.loads.readmodel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRepository extends JpaRepository<Load, Integer> {
    List<Load> findLoadsByDroneIdOrderByLoadDateDesc(Integer droneId);
}
