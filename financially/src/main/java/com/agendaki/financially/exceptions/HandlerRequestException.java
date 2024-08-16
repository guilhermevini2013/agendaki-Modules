package com.agendaki.financially.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandlerRequestException {

    @ExceptionHandler(ExistingDataException.class)
    public ResponseEntity<ResponseErrorModel> handleExistingDataException(ExistingDataException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorModel> handleUsernameNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.unprocessableEntity().body(new ResponseErrorModel(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorModel> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseErrorModel> handIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(new ResponseErrorModel(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ResponseErrorModel> jwtVerificationException(JWTVerificationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseErrorModel(Instant.now(), HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(InternalRequestFailedException.class)
    public ResponseEntity<ResponseErrorModel> internalRequestFailedException(InternalRequestFailedException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ResponseErrorModel(Instant.now(), HttpStatus.SERVICE_UNAVAILABLE.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(SignatureVerificationException.class)
    public ResponseEntity<ResponseErrorModel> internalRequestFailedException(SignatureVerificationException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseErrorModel(Instant.now(), HttpStatus.UNAUTHORIZED.value(), e.getMessage(), request.getRequestURI()));
    }
}
