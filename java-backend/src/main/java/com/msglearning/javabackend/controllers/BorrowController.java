package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.entity.Borrow;
import com.msglearning.javabackend.exceptions.ItemNotFoundException;
import com.msglearning.javabackend.services.BookService;
import com.msglearning.javabackend.services.BorrowService;
import com.msglearning.javabackend.services.ImageService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static com.msglearning.javabackend.controllers.ControllerConstants.*;

@RestController
@RequestMapping({ControllerConstants.API_PATH_BORROW})
public class BorrowController {

    private static final String ALL_PATH = "/all";

    private static final String ID_PATH = "/id/{id}";

   // private static final LocalDate BORROW_DATE = LocalDate.parse("/borrow_date/{localdate}");

   // private static final LocalDate RETURN_DATE = LocalDate.parse("/return_date/{localdate}");


    @Autowired
    private BorrowService borrowService;
    @Autowired
    private Environment env;

    @GetMapping(ALL_PATH)
    public List<Borrow> getAll(){return borrowService.findAll();}
}
