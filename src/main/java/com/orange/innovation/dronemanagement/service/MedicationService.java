package com.orange.innovation.dronemanagement.service;


import com.orange.innovation.dronemanagement.mapper.MedicationMapper;
import com.orange.innovation.dronemanagement.model.Medication;
import com.orange.innovation.dronemanagement.model.dto.MedicationDto;
import com.orange.innovation.dronemanagement.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;

    public MedicationService(MedicationRepository medicationRepository, MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.medicationMapper = medicationMapper;
    }

    public Medication insertMedication(MedicationDto medicationDto) {
        return medicationRepository.save(medicationMapper.toEntity(medicationDto));
    }

    public List<Medication> getAllMedics() {
        return medicationRepository.findAll();
    }

    public Medication getMedication(Long id) {
        return medicationRepository.findById(id).orElseThrow();
    }

}
