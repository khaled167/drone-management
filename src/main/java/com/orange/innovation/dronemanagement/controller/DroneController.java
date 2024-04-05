package com.orange.innovation.dronemanagement.controller;


import com.orange.innovation.dronemanagement.model.Drone;
import com.orange.innovation.dronemanagement.model.DroneMedicationCarry;
import com.orange.innovation.dronemanagement.model.Medication;
import com.orange.innovation.dronemanagement.model.dto.DroneDto;
import com.orange.innovation.dronemanagement.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drone/v1")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<Drone> registerDrone(@RequestBody DroneDto droneDto) {
        return ResponseEntity.ok(droneService.registerDrone(droneDto));
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        return ResponseEntity.ok(droneService.getAllDrones());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getLoadingAvailableDrones() {
        return ResponseEntity.ok(droneService.findLoadingAvailableDrones());
    }

    @GetMapping("/{id}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable Long id) {
        return ResponseEntity.ok(droneService.getLoadedMedications(id));
    }

    @PostMapping("/{droneId}")
    public ResponseEntity<List<DroneMedicationCarry>> loadDrone(@PathVariable Long droneId, @RequestBody Long[] medications) throws Exception {
        return ResponseEntity.ok(droneService.loadDrone(droneId, medications));
    }

    @PostMapping("{id}/end-journey")
    public ResponseEntity<DroneMedicationCarry> endDroneJourney(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(droneService.endDroneJourney(id));
    }
}
