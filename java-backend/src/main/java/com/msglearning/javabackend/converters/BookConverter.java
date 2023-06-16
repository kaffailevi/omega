package com.msglearning.javabackend.converters;

import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.to.BookTO;

public class BookConverter {
    public static final BookTO convertToTO(Book entity) {
        return new BookTO(entity.getId(), entity.getTitle(), entity.getSubtitle(),
                entity.getAuthor(), entity.getPublishingHouse(), entity.getBookCover(), entity.isAvailable());
    }

    public static final Book convertToEntity(BookTO to) {
        return new Book(to.getId(), to.getTitle(), to.getSubTitle(),
                to.getAuthor(), to.getPublishingHouse(), to.getCoverImage(), to.isAvailable(),null);
    }
}