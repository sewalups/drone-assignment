package com.sewalusteven.droneproject.domains.drones.exceptions;

public class DroneExistsException extends RuntimeException{
    public DroneExistsException(String serialNumber) {
        super(String.format("Drone with serial number: %s already exists",serialNumber));
    }
}
