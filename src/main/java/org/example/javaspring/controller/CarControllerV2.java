package org.example.javaspring.controller;

import lombok.RequiredArgsConstructor;
import org.example.javaspring.api.controller.CarsApi;
import org.example.javaspring.api.dto.CarDto;
import org.example.javaspring.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarControllerV2 implements CarsApi {
    private final CarService carService;

    @Override
    public ResponseEntity<CarDto> createCar(CarDto carDto) {
        return ResponseEntity.ok(carService.createCar(carDto));
    }

    @Override
    public ResponseEntity<Void> deleteCar(Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CarDto> getCar(Integer id) {
        return ResponseEntity.of(carService.findCar(id));
    }

    @Override
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carService.getCars());
    }

    @Override
    public ResponseEntity<CarDto> modifyCar(Integer id, CarDto carDto) {
        return ResponseEntity.of(carService.updateCar(id, carDto));
    }
}
