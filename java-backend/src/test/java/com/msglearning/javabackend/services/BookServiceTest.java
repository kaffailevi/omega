package com.msglearning.javabackend.services;

import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;
    @Test
    void shouldSave() {
        Book bookTest = new Book();
        // Set properties of the book object
        bookTest.setTitle("A Song of Ice and Fire");
        bookTest.setSubTitle("A Clash of Kings");
        // Test execution
       // bookService.save(bookTest);

        // Verify the method calls
        verify(bookRepository).save(bookTest);
    }

    @Test
    void shouldFindAll() {

    }

    @Test
    void findByAuthor() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void groupByAuthors() {
    }

    @Test
    void findById() {
    }

    @Test
    void getCoverImage() {
    }

    @Test
    void findBookByCategory() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}