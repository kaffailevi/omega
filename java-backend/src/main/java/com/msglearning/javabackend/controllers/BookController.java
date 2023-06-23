package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.converters.BookConverter;
import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.exceptions.ItemNotFoundException;
import com.msglearning.javabackend.services.BookService;
import com.msglearning.javabackend.services.ImageService;
import com.msglearning.javabackend.to.BookTO;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.msglearning.javabackend.controllers.ControllerConstants.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ControllerConstants.API_PATH_BOOK})
public class BookController {
    private static final String ALL_PATH = "/all";
    private static final String ID_PATH = "/id/{id}";
    private static final String AUTHOR_PATH = "/author/{name}";
    private static final String TITLE_PATH = "/title/{title}";
    private static final String SAVE_PATH = "/new";
    private static final String COVER_IMAGE_PATH = "/cover/{id}";
    private static final String DELETE_PATH = "/delete/{id}";
    private static final String UPDATE_PATH = "/update";


    @Autowired
    private BookService bookService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private Environment env;

    @GetMapping(ALL_PATH)
    public List<BookTO> getAll() {
        return bookService.findAll();
    }

    @GetMapping(AUTHOR_PATH)
    public List<BookTO> getBooksByAuthor(@PathVariable String name) {
        return bookService.findByAuthor(name).stream().map(BookConverter::convertToTO).collect(Collectors.toList());
    }

    @GetMapping(TITLE_PATH)
    public List<BookTO> getBooksByTitle(@PathVariable String title) {
        return bookService.findByTitle(title).stream().map(BookConverter::convertToTO).collect(Collectors.toList());
    }

    @GetMapping(ID_PATH)
    public BookTO getBookById(@PathVariable Long id) throws ItemNotFoundException {
        Optional<Book> opBook = bookService.findById(id);
        if (opBook.isPresent())
            return BookConverter.convertToTO(opBook.get());
        else throw new ItemNotFoundException(NOT_FOUND_MESSAGE + "id=" + id);
    }

    @GetMapping(value = COVER_IMAGE_PATH, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getCoverImage(@PathVariable Long id) throws IOException {
        Optional<String> imageNameOpt= bookService.getCoverImage(id);
        if (imageNameOpt.isEmpty()) {
            return new byte[0];
        }
        String coverImageStorageSpace = env.getProperty("coverimage.path");
        return imageService.read(coverImageStorageSpace +"\\"+imageNameOpt.get());
    }
    @PostMapping(SAVE_PATH)
    public boolean saveBook(@RequestBody BookTO bookTO) {
        try {
            bookService.save(bookTO);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @DeleteMapping(DELETE_PATH)
    public void deleteBook(@PathVariable Long id){ bookService.deleteById(id); }

    @PutMapping(UPDATE_PATH)
    public boolean updateBook(@RequestBody BookTO bookTO){
        boolean res = bookService.update(bookTO);
        System.out.println(res);
        return res;
    }
}
