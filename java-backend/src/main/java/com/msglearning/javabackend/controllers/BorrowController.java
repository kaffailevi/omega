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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

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

    private static LocalDate checkDate(String date) throws DataFormatException
    {
        String[] tmp = date.trim().split("-");
        Integer year =1;
        Integer month=1;
        Integer day=1;
        try {

            switch (tmp.length) {
                case 1: {
                    year = Integer.parseInt(tmp[0]);
                    break;
                }
                case 2: {
                    year = Integer.parseInt(tmp[0]);
                    month = Integer.parseInt(tmp[1]);
                    break;
                }
                case 3: {
                    year = Integer.parseInt(tmp[0]);
                    month = Integer.parseInt(tmp[1]);
                    day = Integer.parseInt(tmp[2]);
                    break;
                }
                default:
                    throw new DataFormatException("INVALID DATE FORMAT");
            }
        }
        catch (Exception e)
        {
            throw new DataFormatException("INVALID DATE FORMAT");
        }

        return LocalDate.of(year,month,day);
    }

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
    public List<BorrowTO> findByLoanDateBetween(@PathVariable String startDate, @PathVariable String endDate) throws DataFormatException {

        String[] temp1 = startDate.split("-");
        String[] temp2 = endDate.split("-");
        LocalDate sd;
        LocalDate ed;
        try{
            sd = checkDate(startDate);
            ed = checkDate(endDate);
            return borrowService.findByLoanDateBetween(sd,ed);
        }
        catch (DataFormatException e)
        {
            throw new DataFormatException(e.getMessage());
        }
    }
    //TODO: POST + PUT mapping methods
}