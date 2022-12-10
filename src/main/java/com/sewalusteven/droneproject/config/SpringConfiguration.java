package com.sewalusteven.droneproject.config;

import com.github.javafaker.Faker;
import com.sewalusteven.droneproject.domains.drones.readmodel.Drone;
import com.sewalusteven.droneproject.domains.drones.readmodel.DroneRepository;
import com.sewalusteven.droneproject.domains.drones.types.DroneModel;
import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import com.sewalusteven.droneproject.domains.medications.readmodel.Medication;
import com.sewalusteven.droneproject.domains.medications.readmodel.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SpringConfiguration implements CommandLineRunner {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    public SpringConfiguration(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDrones();
        this.seedMedications();
    }

    private void seedDrones(){
        if(droneRepository.count() == 0){
            List<Drone> dronesToSeed = new ArrayList<>();

            for (int i = 1; i <= 10 ; i++) {
                Faker faker = new Faker();
                var battery = faker.number().numberBetween(0,100);
                var weightLimit = faker.number().numberBetween(100,500);
                if(i%2 == 0){
                    if(i > 5){
                        dronesToSeed.add(new Drone(faker.code().imei(), DroneModel.Cruiserweight,weightLimit,battery, DroneState.IDLE));
                    }else{
                        dronesToSeed.add(new Drone(faker.code().imei(), DroneModel.Lightweight,weightLimit,battery, DroneState.IDLE));
                    }

                }else{
                    if(i > 5){
                        dronesToSeed.add(new Drone(faker.code().imei(), DroneModel.Heavyweight,weightLimit,battery, DroneState.IDLE));
                    }else{
                        dronesToSeed.add(new Drone(faker.code().imei(), DroneModel.Middleweight,weightLimit,battery, DroneState.IDLE));
                    }
                }

            }

            droneRepository.saveAll(dronesToSeed);
        }

    }

    private void seedMedications(){
        if(medicationRepository.count() == 0){
            List<Medication> medications = new ArrayList<>();

            for (int i = 1; i < 200 ; i++) {
                Faker faker = new Faker();
                medications.add(
                        new Medication(
                                faker.medical().medicineName(),
                                faker.number().numberBetween(2,300),
                                faker.code().isbn10(),
                                String.format("https://picsum.photos/500/300?random=%s",faker.number().numberBetween(1,300))));

            }

            medicationRepository.saveAll(medications);
        }

    }
}
