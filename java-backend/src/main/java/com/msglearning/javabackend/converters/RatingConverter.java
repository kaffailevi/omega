package com.msglearning.javabackend.converters;

import com.msglearning.javabackend.entity.Rating;
import com.msglearning.javabackend.to.RatingTO;

public class RatingConverter {
    public static final RatingTO convertToTO(Rating entity){
        return new RatingTO(
                entity.getId(),
                entity.getBook().getId(),
                entity.getUser().getId(),
                entity.getRating(),
                entity.getReview_title(),
                entity.getReview()
        );
    }
    public static final Rating convertToEntity(RatingTO ratingTO){
        return new Rating(
                ratingTO.getId(),
                ratingTO.getRating(),
                ratingTO.getReview_title(),
                ratingTO.getReview()
                //ratingTO.getDate(),
                );
    }

}
