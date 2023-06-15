package com.msglearning.javabackend.entity;


import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Borrow.TABLE_NAME)
public class Borrow {
    static final String TABLE_NAME = "borrows";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column
    private LocalDate loanDate;
    @Column
    private LocalDate returnDate;

}
