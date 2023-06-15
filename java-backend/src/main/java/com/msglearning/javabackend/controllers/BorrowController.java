package com.msglearning.javabackend.controllers;

import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.entity.Borrow;
import com.msglearning.javabackend.exceptions.ItemNotFoundException;
import com.msglearning.javabackend.services.BookService;
import com.msglearning.javabackend.services.BorrowService;
import com.msglearning.javabackend.services.ImageService;
import com.msglearning.javabackend.to.BorrowTO;
import org.apache.tomcat.jni.Local;
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

    private static final String ALL_BY_USER_ID_PATH = "/all_by_userid/{id}";
    private static final String ALL_BY_BOOK_ID_PATH = "/all_by_bookid/{id}";

    private static final String LOAN_BETWEEN_PATH = "/loan_between/{startDate}/{endDate}";

    // private static final LocalDate BORROW_DATE = LocalDate.parse("/borrow_date/{localdate}");

    // private static final LocalDate RETURN_DATE = LocalDate.parse("/return_date/{localdate}");


    @Autowired
    private BorrowService borrowService;

    @GetMapping(ALL_PATH)
    public List<BorrowTO> getAll() {
        return borrowService.findAll();
    }

    @GetMapping(ALL_BY_USER_ID_PATH)
    public List<BorrowTO> getAllByUserID(@PathVariable Long id)
    {
        return borrowService.findByUserId(id);
    }

    @GetMapping(ALL_BY_BOOK_ID_PATH)
    public List<BorrowTO> getAllByBookID(@PathVariable Long id)
    {
        return borrowService.findByBookId(id);
    }

    // You should use that way: http://localhost:8080/java-api/api/borrow/loan_between/2023-06-01/2023-06-17
    // Important that you must use '-' to separate the year, month, day
    @GetMapping(LOAN_BETWEEN_PATH)
    public List<BorrowTO> findByLoanDateBetween(@PathVariable String startDate, @PathVariable String endDate) {

        String[] temp1 = startDate.split("-");
        String[] temp2 = endDate.split("-");

        LocalDate sd = LocalDate.of(Integer.parseInt(temp1[0]), Integer.parseInt(temp1[1]), Integer.parseInt(temp1[2]));
        LocalDate ed = LocalDate.of(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]));

        return borrowService.findByLoanDateBetween(sd,ed);
    }
}