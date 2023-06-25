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
import java.util.stream.Collector;
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
        Optional<User> opUser = userRepository.findById(ratingTO.getUser_id());
        Optional<Book> opBook = bookRepository.findById(ratingTO.getBook_id());
        if(!opUser.isPresent() || !opBook.isPresent())
            return false;
        rating.setUser(opUser.get());
        rating.setBook(opBook.get());

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
    public List<RatingTO> findByBookId(Long id){
        return ratingRepository.findByBookId(id).stream()
                .map(RatingConverter::convertToTO).collect(Collectors.toList());
    }
    public List<RatingTO> findByUserId(Long id) {
        return ratingRepository.findByUserId(id).stream()
                .map(RatingConverter::convertToTO).collect(Collectors.toList());
    }

}
