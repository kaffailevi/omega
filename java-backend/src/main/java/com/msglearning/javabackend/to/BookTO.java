package com.msglearning.javabackend.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTO {
    private Long id;
    private String title;
    private String subTitle;
    private String author;
    private String publishingHouse;
    private String coverImage;
    private String category;
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
                ", category'" + category + '\'' +
                ", is_available=" + isAvailable +
                '}';
    }




    public void setCategory(String category) {
        this.category = category;
    }
}
