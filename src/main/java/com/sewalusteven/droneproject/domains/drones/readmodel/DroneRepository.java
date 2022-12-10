package com.sewalusteven.droneproject.domains.drones.readmodel;

import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Integer> {
    List<Drone> findDronesByState(DroneState state);

    @Query("SELECT s FROM Drone s WHERE s.serialNumber=?1")
    Optional<Drone> findDroneBySerialNumber(String serialNumber);
}
