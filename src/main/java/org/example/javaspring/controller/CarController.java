package org.example.javaspring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.javaspring.dto.CarDTO;
import org.example.javaspring.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable int id){
        return ResponseEntity.of(carService.getCar(id));
    }
    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ){
     return ResponseEntity.ok(carService.getCars(minEnginePower, maxEnginePower));
    }
    @PostMapping("/cars")
    public ResponseEntity<CarDTO> createCar(@RequestBody @Valid CarDTO car){
        return ResponseEntity.ok(carService.createCar(car));
    }
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable int id, @RequestBody @Valid CarDTO car){
        return ResponseEntity.of(carService.updateCar(id, car));
    }
    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteById(id);
    }

}
