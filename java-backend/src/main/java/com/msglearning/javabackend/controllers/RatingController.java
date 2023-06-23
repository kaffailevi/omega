package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.converters.RatingConverter;
import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.entity.Rating;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.services.BookService;
import com.msglearning.javabackend.services.RatingService;
import com.msglearning.javabackend.services.UserService;
import com.msglearning.javabackend.to.RatingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ControllerConstants.API_PATH_RATING})
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController {
    public static final String USER_ID_PATH = "/userId/{userId}";
    public static final String BOOK_ID_PATH = "/bookId/{bookId}";
    public static final String SAVE_PATH = "/save";
    public static final String DELETE_PATH = "/delete/{ratingId}";

    @Autowired
    RatingService ratingService;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @GetMapping(BOOK_ID_PATH)
    public List<RatingTO> getByBookId(@PathVariable Long bookId){
        return ratingService.findByBookId(bookId);
    }
    @GetMapping(USER_ID_PATH)
    public List<RatingTO> getByUserId(@PathVariable Long userId){
        return ratingService.findByUserId(userId);
    }
    @PostMapping(SAVE_PATH)
    public Boolean save(@RequestBody RatingTO ratingTO){
        return ratingService.save(ratingTO);
    }
    @DeleteMapping(DELETE_PATH)
    public Boolean delete(@PathVariable Long ratingId) {
        return ratingService.deleteById(ratingId);
    }



}
