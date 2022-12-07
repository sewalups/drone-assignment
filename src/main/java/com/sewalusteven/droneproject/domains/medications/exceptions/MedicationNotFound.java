package com.sewalusteven.droneproject.domains.medications.exceptions;

public class MedicationNotFound extends RuntimeException{
    public MedicationNotFound(int id) {
        super(String.format("Medication %s not found",id));
    }

    public MedicationNotFound() {
        super("Medication Not Found");
    }
}
