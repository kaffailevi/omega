package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.services.RatingService;
import com.msglearning.javabackend.to.RatingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({ControllerConstants.API_PATH_RATING})
public class RatingController {
    public static final String USER_ID_PATH = "/userId/{userId}";
    public static final String BOOK_ID_PATH = "/bookId/{bookId}";
    public static final String SAVE_PATH = "/save";
    public static final String DELETE_PATH = "/delete/{ratingId}";

    @Autowired
    RatingService ratingService;
    @GetMapping(BOOK_ID_PATH)
    public Optional<RatingTO> getByBookId(@PathVariable Long book_id){
        return ratingService.findByBookId(book_id);
    }
    @GetMapping(USER_ID_PATH)
    public Optional<RatingTO> getByUserId(@PathVariable Long user_id){
        return ratingService.findByUserId(user_id);
    }
    @GetMapping(SAVE_PATH)
    public Boolean save(@RequestBody RatingTO ratingTO){
        return ratingService.save(ratingTO);
    }
    @DeleteMapping(DELETE_PATH)
    public Boolean delete(@PathVariable Long ratingId) {
        return ratingService.deleteById(ratingId);
    }
}
