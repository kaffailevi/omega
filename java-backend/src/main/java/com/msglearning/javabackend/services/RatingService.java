package com.msglearning.javabackend.services;

import com.msglearning.javabackend.converters.RatingConverter;
import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.entity.Rating;
import com.msglearning.javabackend.entity.RatingEnum;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.repositories.BookRepository;
import com.msglearning.javabackend.repositories.RatingRepository;
import com.msglearning.javabackend.repositories.UserRepository;
import com.msglearning.javabackend.to.RatingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public Boolean save(RatingTO ratingTO){
        Rating rating= RatingConverter.convertToEntity(ratingTO);
        rating.setUser(userRepository.findById(ratingTO.getUser_id()).get());
        rating.setBook(bookRepository.findById(ratingTO.getBook_id()).get());
        //rating.setDate(LocalDate.now());
        if(!validateUser(rating.getUser())){
            return false;
        }
        if(!validateBook(rating.getBook())){
            return false;
        }
        if (!validateRating(rating.getRating())){
            return false;
        }
        if (!validateReviewTitle(rating.getReview_title())){
            return false;
        }
        if(!validateReview(rating.getReview())){
            return true;
        }
        if(!validateDate(rating.getDate())){
            return true;
        }
        ratingRepository.save(rating);
        return true;
    }
    private Boolean validateUser(User user) {
         return user != null;
    }
    private Boolean validateBook(Book book) {
        return book != null;
    }
    private Boolean validateDate(LocalDate date) {
        return date != null;
    }
    private Boolean validateRating(RatingEnum rating) {
        return false;
    }
    private Boolean validateReviewTitle(String review_title){
        // in case we want to validate something about the review title
        return true;
    }
    private Boolean validateReview(String review){
        // in case we want to validate something about the review title
        return true;
    }
    public void delete(RatingTO ratingTO) {
        ratingRepository.delete(RatingConverter.convertToEntity(ratingTO));
    }
    public Boolean deleteById(Long ratingId) {
        try {
            ratingRepository.deleteById(ratingId);
        }
        catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }
    public Optional<RatingTO> findByBookId(Long id){
        return ratingRepository.findByBookId(id)
                .map(RatingConverter::convertToTO);
    }
    public Optional<RatingTO> findByUserId(Long id) {
        return ratingRepository.findByUserId(id)
                .map(RatingConverter::convertToTO);
    }

}
