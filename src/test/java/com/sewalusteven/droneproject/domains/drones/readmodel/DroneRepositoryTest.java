package com.sewalusteven.droneproject.domains.drones.readmodel;

import com.sewalusteven.droneproject.DroneprojectApplication;
import com.sewalusteven.droneproject.domains.drones.types.DroneModel;
import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DroneprojectApplication.class)
class DroneRepositoryTest {
    @Autowired
    private DroneRepository repository;
    private Drone drone;

    @BeforeEach
    void setUp() {
       drone = new Drone(
                "someRandom12Gen",
                DroneModel.Cruiserweight,
                233,
                56,
                DroneState.IDLE
        );
        repository.save(drone);
    }

    @AfterEach
    void tearDown() {
        repository.delete(drone);
    }

    @Test
    public void shouldGetAll(){
        var all = repository.findAll();
        assertNotNull(all);
    }

    @Test
    public void testCanFindDronesByState() {



        var expected = repository.findDronesByState(drone.getState());
        assertNotNull(expected);

        assertTrue(expected
                .stream()
                .anyMatch(e ->
                        Objects.equals(e.getState(), DroneState.IDLE) && Objects.equals(e.getSerialNumber(), "someRandom12Gen")));

    }

    @Test
    public void testCantFindDroneBySerialNumber() {

        var expected = repository.findDroneBySerialNumber(drone.getSerialNumber());
        assertTrue(expected.isPresent());
        assertEquals(expected.get().getSerialNumber(), drone.getSerialNumber());
    }
}