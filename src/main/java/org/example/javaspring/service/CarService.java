package org.example.javaspring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.javaspring.api.dto.CarDto;
import org.example.javaspring.dto.ReviewDTO;
import org.example.javaspring.entity.Car;
import org.example.javaspring.entity.Review;
import org.example.javaspring.mapper.CarMapper;
import org.example.javaspring.mapper.ReviewMapper;
import org.example.javaspring.repository.CarRepository;
import org.example.javaspring.repository.ReviewRopository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ReviewRopository reviewRopository;
    private final ReviewMapper reviewMapper;
    private final CarMapper carMapper;

    public ReviewDTO createReview(Integer carId, ReviewDTO reviewDTO) {
        if(!this.carRepository.existsById(carId)){
            throw new IllegalArgumentException("Car with id " + carId + " does not exist");
        }
        final Review review = reviewMapper.mapToEntity(reviewDTO);
        review.setCarId(carId);
        review.setTimestamp(LocalDateTime.now());
        return this.reviewMapper.mapToDto(this.reviewRopository.save(review));
    }
    public List<ReviewDTO> findAllReviewsForCar(Integer carId){
        return this.reviewRopository.findAllByCarId(carId)
                .stream()
                .map(this.reviewMapper::mapToDto)
                .toList();
    }
    public List<ReviewDTO> getLatestReviews (LocalDateTime startDateTime){
        return this.reviewRopository
                .findAllByTimestampAfter(startDateTime)
                .stream()
                .map(this.reviewMapper::mapToDto)
                .toList();
    }
    public CarDto createCar(CarDto carDto){
        if(carDto.getId() != null){
            throw new IllegalArgumentException("Car can not be created with predefined id");
        }
        Car car = this.carMapper.mapToEntity(carDto);
        Car savedCar = carRepository.save(car);
        return this.carMapper.mapToDto(savedCar);
    }

    public void deleteCar(Integer carId){
        if(!this.carRepository.existsById(carId)){
            throw new IllegalArgumentException("Car with id " + carId + " does not exist");
        }
        this.carRepository.deleteById(carId);
    }
    public Optional<CarDto> findCar(Integer carId){
        return carRepository.findById(carId).map(carMapper::mapToDto);
    }
    public List<CarDto> getCars(){
        return this.carRepository.findAll()
                .stream()
                .map(carMapper::mapToDto)
                .toList();
    }
    @Transactional
    public Optional<CarDto> updateCar(Integer carId, CarDto carDtoUpdateWith){
       return carRepository.findById(carId)
               .map(existingCar ->carMapper.updateEntity(existingCar, carDtoUpdateWith))
               .map(carMapper::mapToDto);
    }
}
