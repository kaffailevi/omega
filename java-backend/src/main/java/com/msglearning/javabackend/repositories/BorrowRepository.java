package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Borrow;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    List<Borrow> findAll();

//    @Query("SELECT b FROM Borrow b WHERE b.user)
    List<Borrow> findAllByUser_Id(Long userId);

    List<Borrow> findAllByBook_Id(Long bookId);

    List<Borrow> findAllByBorrowDateBetween(LocalDate startDate, LocalDate endDate);

    List<Borrow> findAllByReturnDateIsNull();

}
