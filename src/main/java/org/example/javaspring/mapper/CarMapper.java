package org.example.javaspring.mapper;

import org.example.javaspring.api.dto.CarDto;
import org.example.javaspring.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CarMapper {
    CarDto mapToDto(Car car);
    Car mapToEntity(CarDto carDto);
    Car updateEntity(@MappingTarget Car car, CarDto carDto);
}
