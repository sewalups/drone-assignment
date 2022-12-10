package com.sewalusteven.droneproject.domains.medications.handler;

import com.sewalusteven.droneproject.domains.medications.payloads.MedicationPayloads;
import com.sewalusteven.droneproject.domains.medications.readmodel.Medication;
import com.sewalusteven.droneproject.domains.medications.readmodel.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {
    private final MedicationRepository medStore;

    @Autowired
    public MedicationService(MedicationRepository medStore) {
        this.medStore = medStore;
    }

    public void saveMedication(MedicationPayloads.Request medication){
        //TODO validate name and code for the medication
        medStore.save(new Medication(medication.name(),medication.weight(),medication.code(), medication.imagePath()));
    }
    public List<Medication> getAll(){
        //TODO validate name and code for the medication
       return medStore.findAll();
    }
}
