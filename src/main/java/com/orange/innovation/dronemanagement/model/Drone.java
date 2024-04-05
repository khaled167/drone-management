package com.orange.innovation.dronemanagement.model;


import com.orange.innovation.dronemanagement.model.enums.Model;
import com.orange.innovation.dronemanagement.model.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(max = 100)
    private String serialNumber;

    @Column
    private Model model;

    @Column
    @Min(0)
    @Max(500)
    private Short weightLimit;

    @Column
    @Min(0)
    @Max(100)
    private Byte batteryPercentage;

    @Column
    private State state;

}
