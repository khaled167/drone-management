package com.orange.innovation.dronemanagement.controller;


import com.orange.innovation.dronemanagement.model.Medication;
import com.orange.innovation.dronemanagement.model.dto.MedicationDto;
import com.orange.innovation.dronemanagement.service.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medication/v1")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<Medication> addMedication(@RequestBody MedicationDto medicationDto) {
        return ResponseEntity.ok(medicationService.insertMedication(medicationDto));
    }
    @GetMapping
    public ResponseEntity<List<Medication>> getMedications() {
        return ResponseEntity.ok(medicationService.getAllMedics());
    }
}
