package org.example.javaspring.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@Builder
@Document("reviews")
public class Review {
    @MongoId
    private ObjectId id;

    private Integer carId;
    private Integer rating;
    private String text;
    private LocalDateTime timestamp;
}
