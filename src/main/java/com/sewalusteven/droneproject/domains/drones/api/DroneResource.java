package com.sewalusteven.droneproject.domains.drones.api;

import com.sewalusteven.droneproject.domains.drones.handler.DroneService;
import com.sewalusteven.droneproject.domains.drones.payloads.DronePayloads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RequestMapping(path = "api/v1/drone")
@RestController
public class DroneResource {
    private final DroneService service;

    @Autowired
    public DroneResource(DroneService service) {
        this.service = service;
    }

    @GetMapping
    public List<DronePayloads.Response> showDrones(){

        return new ArrayList<>();
    }
}
