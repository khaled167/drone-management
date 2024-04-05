package com.orange.innovation.dronemanagement.mapper;

import com.orange.innovation.dronemanagement.model.Medication;
import com.orange.innovation.dronemanagement.model.dto.MedicationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicationMapper {
    Medication toEntity(MedicationDto medicationDto);

    MedicationDto toDto(Medication medication);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Medication partialUpdate(MedicationDto medicationDto, @MappingTarget Medication medication);
}