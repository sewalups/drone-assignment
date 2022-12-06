package com.sewalusteven.droneproject.domains.drones.readmodel;

import com.sewalusteven.droneproject.domains.drones.types.DroneModel;
import com.sewalusteven.droneproject.domains.drones.types.DroneState;
import jakarta.persistence.*;


@Entity
public class Drone {
    @Id
    @SequenceGenerator(
            name = "drone_sequence",
            sequenceName = "drone_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drone_id_sequence"
    )
    private Integer id;
    private String serialNumber;
    private DroneModel model;
    private Integer weight;
    private Integer battery;
    private DroneState state;

    public Drone() {
    }

    public Drone(Integer id, String serialNumber, DroneModel model, Integer weight, Integer battery, DroneState state) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.battery = battery;
        this.state = state;
    }

    public Drone(String serialNumber, DroneModel model, Integer weight, Integer battery, DroneState state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weight = weight;
        this.battery = battery;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
