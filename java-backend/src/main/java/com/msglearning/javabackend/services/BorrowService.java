package com.msglearning.javabackend.services;


import com.msglearning.javabackend.converters.BorrowConverter;
import com.msglearning.javabackend.entity.Book;
import com.msglearning.javabackend.entity.Borrow;
import com.msglearning.javabackend.entity.User;
import com.msglearning.javabackend.repositories.BorrowRepository;
import com.msglearning.javabackend.to.BorrowTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public Borrow save(Borrow borrow) {

        return borrowRepository.save(borrow);

    }

    @Transactional
    public void deleteById(Long id) {
        borrowRepository.deleteById(id);
    }

    public List<BorrowTO> findAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        if (borrows.isEmpty())
            return Collections.emptyList();

        return borrows.stream()
                .map(BorrowConverter::convertToTO)
                .collect(Collectors.toList());
    }

    public List<BorrowTO> findByUserId(Long userId) {
        List<Borrow> books = borrowRepository.findAllByUserId(userId);
        return books.stream()
                .map(BorrowConverter::convertToTO)
                .collect(Collectors.toList());
    }
    public List<BorrowTO> findByBookId(Long bookId) {
        List<Borrow> books = borrowRepository.findAllByBookId(bookId);
        return books.stream()
                .map(BorrowConverter::convertToTO)
                .collect(Collectors.toList());
    }

    public List<BorrowTO> findByLoanDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Borrow> borrows = borrowRepository.findAllByLoanDateBetween(startDate,endDate);

        return borrows.stream()
                .map(BorrowConverter::convertToTO)
                .collect(Collectors.toList());
    }

    public List<BorrowTO> findAllByReturnDateLessThan(LocalDate reffDate) {
        List<Borrow> borrows = borrowRepository.findAllByReturnDateLessThan(reffDate);

        return borrows.stream()
                .map(BorrowConverter::convertToTO)
                .collect(Collectors.toList());
    }

    public boolean update(BorrowTO borrowTO) {
        Optional<Borrow> optionalBorrow = borrowRepository.findById(borrowTO.getId());

        Optional<User> optionalUser = userService.findById(borrowTO.getUserId());
        if (!(optionalUser.isPresent())) {
            return false;
        }

        Optional<Book> optionalBook = bookService.findById(borrowTO.getBookId());
        if (!(optionalBook.isPresent())) {
            return false;
        }

        optionalBorrow.ifPresent(
                updateBorrow -> {
                    updateBorrow.setUser(optionalUser.get());
                    updateBorrow.setBook(optionalBook.get());
                    updateBorrow.setLoanDate(borrowTO.getLoanDate());
                    updateBorrow.setReturnDate(borrowTO.getReturnDate());
                }
        );

        return optionalBorrow.isPresent();
    }

    public Optional<BorrowTO> findSoonestByBookId(Long id) {
         List<BorrowTO> borrowTOs = borrowRepository.findAllByBookId(id).stream().map(BorrowConverter::convertToTO).collect(Collectors.toList());

         return borrowTOs.stream().max(new Comparator<BorrowTO>() {
             @Override
             public int compare(BorrowTO o1, BorrowTO o2) {
                 return o1.getReturnDate().compareTo(o2.getReturnDate());
             }
         });
    }
}