package org.example.javaspring.controller;

import lombok.RequiredArgsConstructor;
import org.example.javaspring.entity.Car;
import org.example.javaspring.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarRepository carRepository;
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id){
        return ResponseEntity.of(carRepository.findById(id));
    }
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ){
       if(minEnginePower !=null && maxEnginePower !=null){
           return ResponseEntity.ok(carRepository.findAllByEnginePowerBetween(minEnginePower, maxEnginePower));
       }else if(minEnginePower !=null){
           return ResponseEntity.ok(carRepository.findAllByEnginePowerGreaterThan(minEnginePower));
       } else if (maxEnginePower != null) {
           return ResponseEntity.ok(carRepository.findAllByEnginePowerLessThan(maxEnginePower));
       }else{
           return ResponseEntity.ok(carRepository.findAll());
       }
    }
    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car car){
        return ResponseEntity.of(
                carRepository.findById(id)
                        .map(oldCar ->{
                            oldCar.setModel(car.getModel());
                            oldCar.setEnginePower(car.getEnginePower());
                            return carRepository.save(oldCar);
                        })
                );
    }
    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id){
        carRepository.deleteById(id);
    }

}
