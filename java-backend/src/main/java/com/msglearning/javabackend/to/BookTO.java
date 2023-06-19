package com.msglearning.javabackend.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTO implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String author;
    private String publishingHouse;
    private String coverImage;
    private boolean isAvailable;

    @Override
    public String toString() {
        return "BookTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", is_available=" + isAvailable +
                '}';
    }
}
