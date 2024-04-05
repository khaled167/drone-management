package com.orange.innovation.dronemanagement.repository;

import com.orange.innovation.dronemanagement.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
