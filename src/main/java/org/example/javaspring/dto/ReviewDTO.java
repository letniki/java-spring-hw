package org.example.javaspring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ReviewDTO {

    private Integer rating;
    private String text;
    private LocalDateTime timestamp;
}
