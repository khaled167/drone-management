package com.orange.innovation.dronemanagement.repository;

import com.orange.innovation.dronemanagement.model.DroneMedicationCarry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DroneMedicationCarryRepository extends JpaRepository<DroneMedicationCarry, Long> {

    List<DroneMedicationCarry> findAllByDroneId(Long id);

    Optional<DroneMedicationCarry> findByDroneIdAndEndDate(Long droneId, Date endDate);

}
