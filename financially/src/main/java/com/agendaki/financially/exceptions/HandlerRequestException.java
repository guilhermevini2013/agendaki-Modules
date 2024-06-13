package com.agendaki.financially.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerRequestException {

    @ExceptionHandler(ExistingDataException.class)
    public ResponseEntity<ResponseErrorModel> handleExistingDataException(ExistingDataException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseErrorModel> handleUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.unprocessableEntity().body(new ResponseErrorModel(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI()));
    }
}
