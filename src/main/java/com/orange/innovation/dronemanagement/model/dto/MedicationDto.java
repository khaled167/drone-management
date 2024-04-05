package com.orange.innovation.dronemanagement.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.orange.innovation.dronemanagement.model.Medication}
 */
@Value
public class MedicationDto implements Serializable {
    String name;
    Integer weight;
    String code;
    String imageURL;
}