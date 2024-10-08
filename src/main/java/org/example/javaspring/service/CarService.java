package org.example.javaspring.service;

import lombok.RequiredArgsConstructor;
import org.example.javaspring.dto.CarDTO;
import org.example.javaspring.entity.Car;
import org.example.javaspring.mapper.CarMapper;
import org.example.javaspring.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    public List<CarDTO> getCars(Integer minEnginePower, Integer maxEnginePower){
        List<Car> cars;
        if(minEnginePower !=null && maxEnginePower !=null){
            cars = carRepository.findAllByEnginePowerBetween(minEnginePower, maxEnginePower);
        }else if(minEnginePower !=null){
            cars = carRepository.findAllByEnginePowerGreaterThan(minEnginePower);
        } else if (maxEnginePower != null) {
            cars = carRepository.findAllByEnginePowerLessThan(maxEnginePower);
        }else{
            cars = carRepository.findAll();
        }
        return cars.stream().map(carMapper::mapToDTO).toList();
    }
    public Optional<CarDTO> getCar(Integer id){
        return carRepository.findById(id).map(carMapper::mapToDTO);
    }
    public CarDTO createCar(CarDTO carDTO){
        Car car = carMapper.mapToEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return carMapper.mapToDTO(savedCar);
    }
    public Optional<CarDTO> updateCar(Integer id, CarDTO carDTO){
        return carRepository
                .findById(id)
                .map(car -> {
                    car.setModel(carDTO.getModel());
                    car.setEnginePower(carDTO.getEnginePower());
                    car.setTorque(carDTO.getTorque());
                    car.setFuelType(carDTO.getFuelType());
                    Car savedCar = carRepository.save(car);
                    return carMapper.mapToDTO(savedCar);
                });
    }
    public void deleteById(Integer id){
        carRepository.deleteById(id);
    }
}
