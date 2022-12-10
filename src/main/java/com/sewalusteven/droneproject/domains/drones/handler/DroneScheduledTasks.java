package com.sewalusteven.droneproject.domains.drones.handler;

import com.sewalusteven.droneproject.domains.drones.readmodel.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class DroneScheduledTasks {
    private final DroneService service;
    private static final Logger log = LoggerFactory.getLogger(DroneScheduledTasks.class);
    private final DroneRepository droneRepository;

    @Autowired
    public DroneScheduledTasks(DroneService service,
                               DroneRepository droneRepository) {
        this.service = service;
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedRate = 120000)
    public void logDroneBatteryLevels(){
        var drones = service.getAllDrones();
        if(Objects.equals(drones.size(),0)){
            throw new RuntimeException("There are no drones registered yet on the platform");
        }

        drones.forEach(d ->{
            log.info(String.format("Drone with serial no: %s has a Battery Percentage of %s as of time %s",d.getSerialNumber(),d.getBattery(), LocalDateTime.now()));
            d.setBattery(d.getBattery() - 2);
            droneRepository.save(d);
        });
    }


}
