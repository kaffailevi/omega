package com.msglearning.javabackend.to;

import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.entity.RatingEnum;
import com.msglearning.javabackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@AllArgsConstructor
@Data
public class RatingTO implements Serializable {
    private Long id;

    private Long book_id;

    private Long user_id;

    private RatingEnum rating;

    private String review_title;

    private String review;

    //?private LocalDate date;

}
