package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Rating;
import com.msglearning.javabackend.entity.RatingEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.DoubleStream;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {

    List<Rating> findAll();
@Query("SELECT r FROM Ratings r WHERE r.rating LIKE %:rating%")
    List<Rating> findByRating(@Param("rating") RatingEnum rating);

    DoubleStream findByBookId(Long id);
}
