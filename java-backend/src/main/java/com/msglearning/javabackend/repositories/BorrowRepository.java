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

    List<Borrow> findAllByBook_Id(Long bookId);

   // List<Borrow> findAllByBorrowDateBetween(SimpleDateFormat startDate, SimpleDateFormat endDate);

    List<Borrow> findAllByReturnDateIsNull();

}
