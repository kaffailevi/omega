package com.msglearning.javabackend.converters;

import com.msglearning.javabackend.entity.Borrow;
import com.msglearning.javabackend.to.BorrowTO;

public class BorrowConverter {

    public static final BorrowTO convertToTO(Borrow entity) {
        return new BorrowTO(entity.getId(), entity.getUser().getId(), entity.getBook().getId(), entity.getLoanDate(), entity.getReturnDate());
    }

    public static final Borrow convertToEntity(BorrowTO to) {
        return new Borrow(to.getId(), null, null, to.getLoanDate(), to.getReturnDate());
    }

}
