package com.orange.innovation.dronemanagement.mapper;

import com.orange.innovation.dronemanagement.model.Drone;
import com.orange.innovation.dronemanagement.model.dto.DroneDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DroneMapper {
    Drone toEntity(DroneDto droneDto);

    DroneDto toDto(Drone drone);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Drone partialUpdate(DroneDto droneDto, @MappingTarget Drone drone);
}