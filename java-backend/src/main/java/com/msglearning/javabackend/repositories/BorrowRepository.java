package com.msglearning.javabackend.repositories;

import com.msglearning.javabackend.entity.Borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findAll();

    @Query( "SELECT b FROM Borrow b where b.user.id = :userId")
    List<Borrow> findAllByUserId(@Param("userId") Long userId);

    @Query( "SELECT b FROM Borrow b where b.book.id = :bookId")
    List<Borrow> findAllByBookId(@Param("bookId") Long bookId);

    @Query("SELECT b FROM Borrow b where b.loanDate >= :startDate and b.loanDate <= :endDate")
    List<Borrow> findAllByLoanDateBetween(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Borrow b where b.returnDate < :reffDate")
    List<Borrow> findAllByReturnDateLessThan(@Param("reffDate") LocalDate reffDate);

    //List<Borrow> findAllByReturnDate();

}
