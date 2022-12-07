package com.sewalusteven.droneproject.domains.loads.exceptions;

public class MedicationNotFoundInLoad extends RuntimeException{
    public MedicationNotFoundInLoad(int medicationId, int loadId) {
        super(String.format("Medication %s not found in load of id %s", medicationId, loadId));
    }
}
