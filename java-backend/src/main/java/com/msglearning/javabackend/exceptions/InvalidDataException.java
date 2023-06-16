package com.msglearning.javabackend.exceptions;

public class InvalidDataException extends Exception {
    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }

}
