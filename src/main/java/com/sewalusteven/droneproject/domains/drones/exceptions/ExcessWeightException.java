package com.sewalusteven.droneproject.domains.drones.exceptions;

public class ExcessWeightException extends IllegalStateException {
    public ExcessWeightException(Integer weight) {
        super(String.format("Weight %s exceeds drone expected limit", weight));
    }
}
