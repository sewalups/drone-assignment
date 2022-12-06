package com.sewalusteven.droneproject.domains.drones.handler;

import com.sewalusteven.droneproject.domains.drones.payloads.DronePayloads;
import com.sewalusteven.droneproject.domains.drones.readmodel.Drone;
import com.sewalusteven.droneproject.domains.drones.readmodel.DroneRepository;
import com.sewalusteven.droneproject.domains.medications.payloads.MedicationPayloads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneService {
    private final DroneRepository store;

    @Autowired
    public DroneService(DroneRepository store) {
        this.store = store;
    }

    public void registerDrone(DronePayloads.Request request) {
        validateDrone(request);
        var drone = new Drone(
                request.serialNumber(),
                request.model(),
                request.weight(),
                request.battery(),
                request.state());
        store.save(drone);
    }

    public void loadDrone(){}

    public List<MedicationPayloads.Response> checkLoadedMedicationsOnDrone(Integer droneId){
        return  new ArrayList<>();
    }

    public List<DronePayloads.Response> findAllFreeDrones(){
        return new ArrayList<>();
    }

    public Integer checkDroneBattery(Integer droneId){
        return 0;
    }

    private void validateDrone(DronePayloads.Request drone) {
        DroneHelper.validateBattery(drone.battery());
        DroneHelper.validateWeight(drone.weight());
        DroneHelper.validateSerialNumber(drone.serialNumber());
    }


}
