package com.sewalusteven.droneproject.domains.drones.handler;

import com.sewalusteven.droneproject.domains.drones.exceptions.DroneNotFound;
import com.sewalusteven.droneproject.domains.drones.payloads.DronePayloads;
import com.sewalusteven.droneproject.domains.drones.readmodel.Drone;
import com.sewalusteven.droneproject.domains.drones.readmodel.DroneRepository;
import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import com.sewalusteven.droneproject.domains.loads.exceptions.LoadNotFound;
import com.sewalusteven.droneproject.domains.loads.readmodel.Load;
import com.sewalusteven.droneproject.domains.loads.readmodel.LoadRepository;
import com.sewalusteven.droneproject.domains.medications.exceptions.MedicationNotFound;
import com.sewalusteven.droneproject.domains.medications.readmodel.Medication;
import com.sewalusteven.droneproject.domains.medications.readmodel.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class DroneService {
    private final DroneRepository store;
    private final MedicationRepository medStore;
    private final LoadRepository loadStore;

    @Autowired
    public DroneService(DroneRepository store, MedicationRepository medStore, LoadRepository loadStore) {
        this.store = store;
        this.medStore = medStore;
        this.loadStore = loadStore;
    }

    public Drone registerDrone(DronePayloads.Request request) {
        validateDrone(request);
        var drone = new Drone(
                request.serialNumber(),
                request.model(),
                request.weight(),
                request.battery(),
                request.state());
        store.save(drone);
        return  drone;
    }

    public List<Drone> getAllDrones(){
        return store.findAll();
    }

    public Load loadDrone(List<Integer> medicationIds, Integer droneId){
        var drone = store.findById(droneId)
                .orElseThrow(() -> new DroneNotFound(droneId));

        if (drone.getBattery() < 25) {
            throw new RuntimeException("Drone cannot be loaded because it does not have enough battery");
        }

        if(!Objects.equals(drone.getState(), DroneState.IDLE)){
            throw new RuntimeException("Drone cannot be loaded because it is being used");
        }

        changeDroneStatus(drone, DroneState.LOADING);

        var medications = new HashSet<>(medStore.findAllById(medicationIds));
        if(medications.isEmpty()){
            throw new MedicationNotFound();
        }
        validateMedicationWeightToDrone(drone, medications);

        var load =  new Load(drone, medications, LocalDate.now());
        loadStore.save(load);
        changeDroneStatus(drone, DroneState.LOADED);

        return load;
    }

    public Set<Medication> checkLatestLoadedMedicationsOnDrone(Integer droneId){
        var drone = store.findById(droneId)
                .orElseThrow(() -> new DroneNotFound(droneId));
        var load =  loadStore.findLoadsByDroneIdOrderByLoadDateDesc(drone.getId())
                .stream()
                .findFirst()
                .orElseThrow(() -> new LoadNotFound(droneId));

        return load.getMedications();
    }


    public List<DronePayloads.Response> findAllFreeDrones(){
        return store.findDronesByState(DroneState.IDLE)
                .stream()
                .map(d -> new DronePayloads.Response(d.getId(),d.getSerialNumber(),d.getModel(),d.getWeight(),d.getBattery(),d.getState()))
                .toList();
    }

    public Integer checkDroneBattery(Integer droneId){
        var drone = store.findById(droneId)
                .orElseThrow(() -> new DroneNotFound(droneId));
        return drone.getBattery();
    }

    private void validateMedicationWeightToDrone(Drone drone, Set<Medication> medications){
        final Integer[] medicationWeight = {0};
        medications.forEach(m -> {
            medicationWeight[0] += m.getWeight();
        });

        if(medicationWeight[0] > drone.getWeight()){
            throw new RuntimeException(String.format("Total Medication Weight %s exceeds Drone weight limit %s", medicationWeight[0], drone.getWeight()));
        }
    }

    private void changeDroneStatus (Drone drone, DroneState state){
        drone.setState(state);
        store.save(drone);
    }

    private void validateDrone(DronePayloads.Request drone) {
        DroneHelper.validateBattery(drone.battery());
        DroneHelper.validateWeight(drone.weight());
        DroneHelper.validateSerialNumber(drone.serialNumber());
    }


}
