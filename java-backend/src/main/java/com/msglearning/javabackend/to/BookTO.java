package com.msglearning.javabackend.to;

import com.msglearning.javabackend.entity.Borrow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTO {


    private Long id;

    private String title;

    private String subtitle;

    private String author;

    private String publishingHouse;

    private String bookCover;

}
