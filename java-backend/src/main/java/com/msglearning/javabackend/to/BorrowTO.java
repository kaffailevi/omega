package com.msglearning.javabackend.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowTO implements Serializable {

    private Long id;

    private Long userId;

    private Long bookId;

    private LocalDate loanDate;

    private LocalDate returnDate;

}
