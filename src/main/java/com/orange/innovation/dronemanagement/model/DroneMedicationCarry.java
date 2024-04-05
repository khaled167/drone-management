package com.orange.innovation.dronemanagement.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DroneMedicationCarry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date startDate;

    @Column
    private String pickupLocation;

    @Column
    private String destinationLocation;

    @Column
    private Date endDate;

    @ManyToOne
    private Drone drone;

    @ManyToOne
    private Medication medication;

    public Boolean isCompleted() {
        return endDate != null;
    }
}
