package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Rating;
import com.msglearning.javabackend.entity.RatingEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {

    /*@Query("SELECT r FROM Rating r WHERE r. LIKE rating%")
    Optional<Rating> findByRating(@Param("rating") RatingEnum rating);*/

    @Query("SELECT r FROM Rating r WHERE r.book.id = :id")
    Optional<Rating> findByBookId(@Param("id") Long id);

    @Query("SELECT r FROM Rating r WHERE r.user.id = :id")
    Optional<Rating> findByUserId(@Param("id") Long id);
}
