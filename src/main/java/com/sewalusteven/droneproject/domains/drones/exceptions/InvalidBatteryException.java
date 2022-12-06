package com.sewalusteven.droneproject.domains.drones.exceptions;

public class InvalidBatteryException extends IllegalStateException{
    public InvalidBatteryException(Integer battery) {
        super(String.format("Battery cannot exceed %s", battery));
    }
}
