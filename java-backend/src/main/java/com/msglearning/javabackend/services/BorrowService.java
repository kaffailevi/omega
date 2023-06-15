package com.msglearning.javabackend.services;


import com.msglearning.javabackend.converters.BorrowConverter;
import com.msglearning.javabackend.entity.Borrow;
import com.msglearning.javabackend.repositories.BorrowRepository;
import com.msglearning.javabackend.to.BorrowTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    public Borrow save(Borrow borrow) throws Exception {
        return borrowRepository.save(borrow);
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
}
