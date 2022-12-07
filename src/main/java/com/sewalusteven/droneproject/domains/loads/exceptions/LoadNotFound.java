package com.sewalusteven.droneproject.domains.loads.exceptions;

public class LoadNotFound extends RuntimeException{
    public LoadNotFound(int droneId) {
        super(String.format("Load not found for drone %s",droneId));
    }
    public LoadNotFound() {
        super("Load not found");
    }
}
