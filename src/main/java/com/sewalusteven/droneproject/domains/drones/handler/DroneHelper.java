package com.sewalusteven.droneproject.domains.drones.handler;

import com.sewalusteven.droneproject.domains.drones.exceptions.ExcessWeightException;
import com.sewalusteven.droneproject.domains.drones.exceptions.InvalidBatteryException;
import com.sewalusteven.droneproject.domains.drones.exceptions.InvalidSerialNumberException;

public class DroneHelper {
    public static void validateBattery(Integer battery){
        if(battery > 100){
            throw new InvalidBatteryException(battery);
        }
    }

    public static void validateWeight(Integer weight){
        if(weight > 500){
            throw new ExcessWeightException(weight);
        }
    }

    public static void validateSerialNumber(String serialNumber){
        var serialArr = serialNumber.toCharArray();
        if(serialArr.length > 100){
            throw new InvalidSerialNumberException(serialNumber);
        }
    }
}
