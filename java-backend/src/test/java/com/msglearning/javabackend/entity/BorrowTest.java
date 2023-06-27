package com.msglearning.javabackend.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BorrowTest {
    Book bookTest = new Book();
    User userTest = new User();
    LocalDate borrowDate = LocalDate.of(2023, 6, 27);
    LocalDate returnDate = LocalDate.of(2023, 7, 10);
    @Test
    void getBook(){
        Borrow borrowTest = new Borrow(null, bookTest, userTest, borrowDate,returnDate );
        assertEquals(bookTest, borrowTest.getBook());
    }

}