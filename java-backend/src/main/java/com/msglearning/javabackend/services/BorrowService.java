package com.msglearning.javabackend.services;


import com.msglearning.javabackend.entity.Borrow;
import com.msglearning.javabackend.repositories.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    public Borrow save(Borrow borrow) throws Exception{
        return borrowRepository.save(borrow);
    }

    public List<Borrow> findAll(){
        List<Borrow> borrows = borrowRepository.findAll();
                if (borrows.isEmpty())
                    return Collections.emptyList();

                return borrows.stream().collect(Collectors.toList());
    }


}
