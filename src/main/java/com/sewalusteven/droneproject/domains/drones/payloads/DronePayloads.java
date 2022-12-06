package com.sewalusteven.droneproject.domains.drones.payloads;

import com.sewalusteven.droneproject.domains.drones.types.DroneModel;
import com.sewalusteven.droneproject.domains.drones.types.DroneState;

public class DronePayloads {
    public record Request(
            String serialNumber, //cannot go more than 100 chars
            DroneModel model,
            Integer weight,
            Integer battery,
            DroneState state
    ){}
    public record Response(
            Integer id,
            String serialNumber,
            DroneModel model,
            Integer weight,
            Integer battery,
            DroneState state
    ){

    }
}
