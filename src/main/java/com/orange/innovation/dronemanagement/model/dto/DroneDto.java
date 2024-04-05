package com.orange.innovation.dronemanagement.model.dto;

import com.orange.innovation.dronemanagement.model.enums.Model;
import com.orange.innovation.dronemanagement.model.enums.State;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.orange.innovation.dronemanagement.model.Drone}
 */
@Value
public class DroneDto implements Serializable {

    String serialNumber;
    Short batteryPercentage;
    State state;
    Model model;
    Short weightLimit;
}