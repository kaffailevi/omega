package com.msglearning.javabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Book.TABLE_NAME)
public class Book {
    static final String TABLE_NAME = "book";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    @Column(nullable = false)
    private String author;
    @Column
    private String publishingHouse;
    @Column(name = "coverImage")
    private String coverImage;
    @Column
    private String category;
    @Column(name = "is_available")
    private boolean isAvailable;
    @OneToMany(mappedBy = "book")
    List<Borrow> borrowList;

    @OneToMany(mappedBy = "book")
    List<Rating> ratingList;
}
