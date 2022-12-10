package com.sewalusteven.droneproject.domains.drones.handler;

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

    @Autowired
    public DroneScheduledTasks(DroneService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 120000)
    public void logDroneBatteryLevels(){
        var drones = service.getAllDrones();
        if(Objects.equals(drones.size(),0)){
            throw new RuntimeException("There are no drones registered yet on the platform");
        }

        drones.forEach(d ->{
            log.info(String.format("Drone with serial no: %s has a Battery Percentage of %s as of time %s",d.getSerialNumber(),d.getBattery(), LocalDateTime.now()));
        });
    }


}
