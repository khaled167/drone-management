package com.orange.innovation.dronemanagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "[a-zA-Z0-9\\-_]*")
    private String name;

    @Column
    private Integer weight;

    @Column
    @Pattern(regexp = "[A-Z0-9_]*")
    private String code;

    @Column(name = "IMAGE_URL")
    private String imageURL;
}
