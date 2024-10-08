package org.example.javaspring.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarDTO {
    private int id;

    @NotBlank
    private String model;
    @NotNull
    @Min(50)
    @Max(3000)
    private int enginePower;
    @NotNull
    @Min(50)
    @Max(500)
    private int torque;
    @NotBlank
    private String fuelType;
}
