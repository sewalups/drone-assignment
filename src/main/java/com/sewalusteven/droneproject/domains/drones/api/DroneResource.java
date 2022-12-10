package com.sewalusteven.droneproject.domains.drones.api;

import com.sewalusteven.droneproject.domains.drones.handler.DroneService;
import com.sewalusteven.droneproject.domains.drones.payloads.DronePayloads;
import com.sewalusteven.droneproject.domains.drones.readmodel.Drone;
import com.sewalusteven.droneproject.domains.loads.readmodel.Load;
import com.sewalusteven.droneproject.domains.medications.readmodel.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping(path = "api/v1/drone")
@RestController
public class DroneResource {
    private final DroneService service;

    @Autowired
    public DroneResource(DroneService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Drone> registerDrone(@RequestBody DronePayloads.Request body) {
        var drone = service.registerDrone(body);
        return new ResponseEntity<>(drone, HttpStatus.OK);

    }

    @PostMapping(path = "/{droneId}/_load")
    public ResponseEntity<Load> loadDrone(@PathVariable("droneId") Integer droneId, @RequestBody DronePayloads.MedicationsLoad medicationsLoad) {
        var load = service.loadDrone(medicationsLoad.medicationIds(), droneId);
        return new ResponseEntity<>(load, HttpStatus.OK);
    }

    @GetMapping(path = "/{droneId}/_medications")
    public ResponseEntity<Set<Medication>> checkLoadForDrone(@PathVariable("droneId") Integer droneId) {
        var medicationLoad = service.checkLatestLoadedMedicationsOnDrone(droneId);
        return new ResponseEntity<>(medicationLoad, HttpStatus.OK);
    }

    @GetMapping(path = "/_available")
    public ResponseEntity<List<DronePayloads.Response>> getFreeDrones(){
        var drones = service.findAllFreeDrones();
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @GetMapping(path = "/{droneId}/_battery")
    public ResponseEntity<Integer> getDroneBattery(@PathVariable("droneId") Integer droneId){
        return new ResponseEntity<>(service.checkDroneBattery(droneId), HttpStatus.OK);
    }
}
