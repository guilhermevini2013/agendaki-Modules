package com.agendaki.financially.exceptions;

public class InternalRequestFailedException extends RuntimeException {
    public InternalRequestFailedException(String message) {
        super(message);
    }
}
