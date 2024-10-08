package com.agendaki.scheduling.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

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

    @ExceptionHandler(CheckTemplateException.class)
    ResponseEntity<ResponseErrorModel> checkTemplate(CheckTemplateException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ResponseErrorModel> duplicateData(DuplicateDataException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseErrorModel> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.unprocessableEntity().body(new ResponseErrorModel(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ResponseErrorModel> jwtVerificationException(JWTVerificationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseErrorModel(Instant.now(), HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request.getRequestURI()));
    }

}
