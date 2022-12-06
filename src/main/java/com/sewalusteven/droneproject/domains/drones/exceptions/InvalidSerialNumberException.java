package com.sewalusteven.droneproject.domains.drones.exceptions;

public class InvalidSerialNumberException extends IllegalStateException{
    public InvalidSerialNumberException(String serial) {
        super(String.format("Serial number %s exceeds 100 characters", serial));
    }
}
