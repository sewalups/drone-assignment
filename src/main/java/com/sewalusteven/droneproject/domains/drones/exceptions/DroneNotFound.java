package com.sewalusteven.droneproject.domains.drones.exceptions;

public class DroneNotFound extends RuntimeException{
    public DroneNotFound(int id) {
        super(String.format("Drone %s not found",id));
    }
}
