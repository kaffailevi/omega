package com.msglearning.javabackend.entity;

import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getTitle(){
        Book bookTest = new Book();
        bookTest.setTitle("A Song of Ice and Fire");
        assertEquals("A Song of Ice and Fire",bookTest.getTitle());

    }
    @Test
    void getSubTitle() {
        Book bookTest = new Book();
        bookTest.setSubTitle("A Clash of Kings");
        assertEquals("A Clash of Kings", bookTest.getSubTitle());
    }

    @Test
    void getAuthor() {
        Book bookTest = new Book();
        bookTest.setAuthor("George R. R. Martin");
        assertEquals("George R. R. Martin", bookTest.getAuthor());
    }

    @Test
    void getPublishingHouse() {
        Book bookTest = new Book();
        bookTest.setPublishingHouse("HarperCollins Publishers UK");
        assertEquals("HarperCollins Publishers UK", bookTest.getPublishingHouse());
    }

    @Test
    void getCoverImage() {
        Book bookTest = new Book();
        bookTest.setCoverImage("https://m.media-amazon.com/images/I/91Nl6NuijHL._AC_UF1000,1000_QL80_.jpg");
        assertEquals("https://m.media-amazon.com/images/I/91Nl6NuijHL._AC_UF1000,1000_QL80_.jpg", bookTest.getCoverImage());
    }

    @Test
    void isAvailable() {
        Book bookTest = new Book();
        bookTest.setAvailable(true);
        assertEquals(true, bookTest.isAvailable());
    }

}