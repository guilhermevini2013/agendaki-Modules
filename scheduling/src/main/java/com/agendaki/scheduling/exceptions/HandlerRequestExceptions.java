package com.agendaki.scheduling.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class HandlerRequestExceptions {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseErrorModel> handlerUsernameNotFoundException(UsernameNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseErrorModel(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getMessage(), request.getServletPath()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorModel> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList(), request.getRequestURI()));
    }
}
