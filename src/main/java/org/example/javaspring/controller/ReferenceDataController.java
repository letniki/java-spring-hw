package org.example.javaspring.controller;

import lombok.RequiredArgsConstructor;
import org.example.javaspring.config.properties.FuelTypes;
import org.example.javaspring.config.properties.ReferenceDataProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reference-data")
@RequiredArgsConstructor
public class ReferenceDataController {

@Value("${reference-data.engine-types}")
private List<String> engineTypes;

private final ReferenceDataProperties referenceDataProperties;

    @GetMapping("/engine-types")
    public List<String> getEngineTypes(){
        return engineTypes;
    }
    @GetMapping("/fuel-types")
    public List<FuelTypes> getFuelTypes(){
        return referenceDataProperties.getFuels();
    }
    @GetMapping("fuel-types/{fuelName}")
    public Optional<FuelTypes> getFuelType(@PathVariable String fuelName){
        Optional<FuelTypes> types = referenceDataProperties.getFuels().stream().filter(fuel -> fuel.getName().equalsIgnoreCase(fuelName)).findFirst();
        return types;

    }
}
