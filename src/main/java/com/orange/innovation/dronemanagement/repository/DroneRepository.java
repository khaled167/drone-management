package com.orange.innovation.dronemanagement.repository;

import com.orange.innovation.dronemanagement.model.Drone;
import com.orange.innovation.dronemanagement.model.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    List<Drone> findAllByStateAndBatteryPercentageGreaterThan(State state, Short batteryPercentage);
}
