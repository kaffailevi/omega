package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Book;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository underTest;
    @Test
    void itShouldFindAll() {
        // Arrange
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book(), new Book());
        underTest.saveAll(expectedBooks);

        // Act
        List<Book> actualBooks = underTest.findAll();

        // Assert
        assertEquals(expectedBooks.size(), actualBooks.size());
    }

    @Test
    void shouldFindByTitle() {

        Book book = new Book();
        book.setTitle("The Great Gatsby");
        underTest.save(book);


        Book foundBook = (Book) underTest.findByTitle("The Great Gatsby");


        assertEquals("The Great Gatsby", foundBook.getTitle());
    }

    @Test
    void shouldFindByAuthor() {

        Book book1 = new Book();
        book1.setAuthor("Jane Austen");
        underTest.save(book1);

        Book book2 = new Book();
        book2.setAuthor("Emily Bronte");
        underTest.save(book2);


        List<Book> foundBooks = underTest.findByAuthor("Jane Austen");


        assertEquals(1, foundBooks.size());


        assertEquals("Jane Austen", foundBooks.get(0).getAuthor());
    }

    @Test
    void shouldFindByPublishingHouse() {

        Book book1 = new Book();
        book1.setPublishingHouse("Penguin Books");
        underTest.save(book1);

        Book book2 = new Book();
        book2.setPublishingHouse("HarperCollins");
        underTest.save(book2);


        List<Book> foundBooks = underTest.findByPublishingHouse("Penguin Books");


        assertEquals(1, foundBooks.size());


        assertEquals("Penguin Books", foundBooks.get(0).getPublishingHouse());
    }


    @Test
    void shouldFindById() {

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setAuthor("John Doe");
        book.setPublishingHouse("Sample Publishing");
        underTest.save(book);


        Long bookId = book.getId();


        Optional<Book> foundBook = underTest.findById(bookId);


        assertTrue(foundBook.isPresent());


        assertEquals(bookId, foundBook.get().getId());


        assertEquals("Sample Book", foundBook.get().getTitle());


        assertEquals("John Doe", foundBook.get().getAuthor());


        assertEquals("Sample Publishing", foundBook.get().getPublishingHouse());
    }

    @Test
    void shouldFindCoverImageById() {

    }

    @Test
    void shouldDeleteById() {

        Book book = new Book();
        book.setTitle("Sample Book");
        book.setAuthor("John Doe");
        book.setPublishingHouse("Sample Publishing");
        underTest.save(book);


        Long bookId = book.getId();


        underTest.deleteById(bookId);


        Book deletedBook = underTest.findById(bookId).orElse(null);


        assertNull(deletedBook);
    }

    @Test
    @Disabled
    void findBookByCategory() {
    }
}