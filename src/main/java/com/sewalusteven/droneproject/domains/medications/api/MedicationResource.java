package com.sewalusteven.droneproject.domains.medications.api;

import com.sewalusteven.droneproject.domains.medications.handler.MedicationService;
import com.sewalusteven.droneproject.domains.medications.readmodel.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "api/v1/medication")
@RestController
public class MedicationResource {
    private final MedicationService service;

    @Autowired
    public MedicationResource(MedicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Medication>> getMedications(){

        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
