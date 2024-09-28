package org.example.javaspring.repository;

import org.example.javaspring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

List<Car> findAllByEnginePowerBetween(int minEnginePower, int maxEnginePower);

List<Car> findAllByEnginePowerGreaterThan(int enginePower);

List<Car> findAllByEnginePowerLessThan(int enginePower);
}
