package com.agendaki.financially.exceptions;

public class ExistingDataException extends RuntimeException {

    public ExistingDataException(String message) {
        super(message);
    }
}
