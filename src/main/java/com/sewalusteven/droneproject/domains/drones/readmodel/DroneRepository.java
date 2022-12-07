package com.sewalusteven.droneproject.domains.drones.readmodel;

import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {
    List<Drone> findDronesByState(DroneState state);
}
