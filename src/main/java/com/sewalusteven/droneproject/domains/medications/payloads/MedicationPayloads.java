package com.sewalusteven.droneproject.domains.medications.payloads;

public class MedicationPayloads {
    public record Request(
            String name,
            Integer weight,
            String code,
            String imagePath
    ) {
        //TODO validate code, name
    }

    public record Response(
            String name,
            Integer weight,
            String code,
            String imagePath
    ) {
    }
}
