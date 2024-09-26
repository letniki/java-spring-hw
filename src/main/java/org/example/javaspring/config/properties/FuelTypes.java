package org.example.javaspring.config.properties;

import lombok.Data;

import java.util.List;
@Data
public class FuelTypes {
    private String name;
    private List<String> types;
}
