package com.orange.innovation.dronemanagement.service;


import com.orange.innovation.dronemanagement.mapper.DroneMapper;
import com.orange.innovation.dronemanagement.model.Drone;
import com.orange.innovation.dronemanagement.model.DroneMedicationCarry;
import com.orange.innovation.dronemanagement.model.Medication;
import com.orange.innovation.dronemanagement.model.dto.DroneDto;
import com.orange.innovation.dronemanagement.model.enums.State;
import com.orange.innovation.dronemanagement.repository.DroneMedicationCarryRepository;
import com.orange.innovation.dronemanagement.repository.DroneRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DroneService {

    private final DroneRepository droneRepository;
    private final DroneMedicationCarryRepository droneMedicationCarryRepository;
    private final DroneMapper droneMapper;
    private final MedicationService medicationService;

    public DroneService(DroneRepository droneRepository, DroneMedicationCarryRepository droneMedicationCarryRepository, DroneMapper droneMapper, MedicationService medicationService) {
        this.droneRepository = droneRepository;
        this.droneMedicationCarryRepository = droneMedicationCarryRepository;
        this.droneMapper = droneMapper;
        this.medicationService = medicationService;
    }

    public Drone registerDrone(@Valid DroneDto droneDto) {
        return droneRepository.save(droneMapper.toEntity(droneDto));
    }

    public List<Drone> findLoadingAvailableDrones() {
        return droneRepository.findAllByStateAndBatteryPercentageGreaterThan(State.IDLE, (short) 25);
    }

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public List<Medication> getLoadedMedications(Long id) {
        return droneMedicationCarryRepository.findAllByDroneId(id).stream().map(DroneMedicationCarry::getMedication).collect(Collectors.toList());
    }

    /**
     * This function should handle multiple simultaneous requests either via a DLMs (Distributed Lock Managers) like Redis or Database Locking
     * and NOT via normal Locking or Synchronizing methods as per Microservices can have multiple instances/pods across multiple nodes and clusters,
     * so each instance has its own dependent resources (RAMs and CPUs)
     */
    public List<DroneMedicationCarry> loadDrone(Long droneId, Long[] medicationsIds) throws Exception {
        Drone drone = getDroneIfAvailability(droneId);
        List<Medication> medications = getMedicationsIfLoadable(medicationsIds, drone);
        return medications.stream().map(medication -> droneMedicationCarryRepository.save(DroneMedicationCarry.builder()
                .drone(drone)
                .startDate(new Date())
                .medication(medication)
                .build())).collect(Collectors.toList());
    }

    private Drone getDroneIfAvailability(Long droneId) throws Exception {
        Drone drone = droneRepository.findById(droneId).orElseThrow(() -> new IllegalArgumentException("No drone exists with id: " + droneId));
        if (drone.getBatteryPercentage() <= 25)
            throw new Exception("Insufficient battery power may lead the drone to shutdown");
        if (drone.getState() != State.IDLE)
            throw new Exception("Drone is currently not available\nTry in another time or find another drone");
        return drone;
    }

    private List<Medication> getMedicationsIfLoadable(Long[] medicationsIds, Drone drone) throws Exception {
        List<Medication> medications = Arrays.stream(medicationsIds).map(medicationService::getMedication).toList();
        if (medications.stream().mapToInt(Medication::getWeight).sum() > drone.getWeightLimit())
            throw new Exception("Maximum weight exceeded for Drone: " + drone.getId());
        return medications;
    }

    public DroneMedicationCarry endDroneJourney(Long id) throws Exception {
        return droneMedicationCarryRepository.save(droneMedicationCarryRepository.findByDroneIdAndEndDate(id, null).orElseThrow(() -> new Exception("Drone has no current journeys")).setEndDate(new Date()));
    }
}
