package org.example.javaspring.repository;

import org.bson.types.ObjectId;
import org.example.javaspring.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRopository extends MongoRepository<Review, ObjectId> {
    List<Review> findAllByCarId(Integer carId);
    List<Review> findAllByTimestampAfter(LocalDateTime startDateTime);

}
