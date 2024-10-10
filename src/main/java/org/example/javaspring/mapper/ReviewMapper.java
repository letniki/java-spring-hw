package org.example.javaspring.mapper;

import org.example.javaspring.dto.ReviewDTO;
import org.example.javaspring.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(imports = LocalDateTime.class)
public interface ReviewMapper {
    ReviewDTO mapToDto(Review review);
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    Review mapToEntity(ReviewDTO reviewDTO);
}
