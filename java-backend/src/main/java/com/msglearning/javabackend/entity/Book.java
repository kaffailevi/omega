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
    @Column
    private String subtitle;
    @Column(nullable = false)
    private String author;
    @Column
    private String publishingHouse;
    @Column(name = "book_cover")
    private String bookCover;

    @OneToMany(mappedBy = "book")
    List<Borrow> borrowList;

}
