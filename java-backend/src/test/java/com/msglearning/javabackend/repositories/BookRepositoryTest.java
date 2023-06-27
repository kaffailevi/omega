package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
@DataJpaTest
@AutoConfigureTestDatabase
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Test
    void itShouldFindById(){
        BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);

        //given
        Book bookTest = new Book();
        bookTest.setId(1L);
        bookTest.setTitle("A Song of Ice and Fire");
        bookTest.setSubTitle("A Clash of Kings");
        bookTest.setAuthor("George R. R. Martin");
        bookTest.setPublishingHouse("HarperCollins Publishers UK");
        bookTest.setCoverImage("https://m.media-amazon.com/images/I/91Nl6NuijHL._AC_UF1000,1000_QL80_.jpg");
        bookTest.setAvailable(true);

        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.of(bookTest));

        Optional<Book> actualBookOptional = bookRepositoryMock.findById(1L);

        assertEquals(Optional.of(bookTest), actualBookOptional);
    }

    @Test
    void itShouldFindByTitle(){


        Book book1 = new Book();
        book1.setTitle("A Song of Ice and Fire");
        book1.setSubTitle("A Clash of Kings");
        book1.setAuthor("George R. R. Martin");
        book1.setPublishingHouse("HarperCollins Publishers UK");
        book1.setCoverImage("https://m.media-amazon.com/images/I/91Nl6NuijHL._AC_UF1000,1000_QL80_.jpg");
        book1.setAvailable(true);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("A Song of Ice and Fire");
        book2.setSubTitle("A Game of Thrones");
        book2.setAuthor("George R. R. Martin");
        book2.setPublishingHouse("HarperCollins Publishers UK");
        book2.setCoverImage("https://m.media-amazon.com/images/I/91Nl6NuijHL._AC_UF1000,1000_QL80_.jpg");
        book2.setAvailable(true);
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("The Lord of the Rings");
        book3.setSubTitle("The Fellowship of the Ring");
        book3.setAuthor("J.R.R. Tolkien");
        book3.setPublishingHouse("Some Publisher");
        book3.setCoverImage("https://m.media-amazon.com/images/I/91Nl6NuijHL._AC_UF1000,1000_QL80_.jpg");
        book3.setAvailable(true);
        bookRepository.save(book3);

        // Perform the findByTitle operation
        List<Book> foundBooks = bookRepository.findByTitle("A Song of Ice and Fire");

        assertEquals(2, foundBooks.size());

        assertEquals("A Song of Ice and Fire", foundBooks.get(0).getTitle());
        assertEquals("A Song of Ice and Fire", foundBooks.get(1).getTitle());
    }
}