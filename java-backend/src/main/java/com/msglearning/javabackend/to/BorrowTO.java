package com.msglearning.javabackend.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowTO implements Serializable {

    private Long id;

    private Long userId;

    private Long bookId;

    private SimpleDateFormat loanDate;

    private SimpleDateFormat returnDate;

}
