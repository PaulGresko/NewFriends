package com.example.NewFriends.controllers.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Exception> handleException(NoSuchElementException e) {
        return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Exception> globalHandleException(HttpServerErrorException.InternalServerError e) {
//        return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResponseEntity<Exception> ForbiddenHandleException(HttpClientErrorException.Forbidden e) {
//        return new ResponseEntity<>(new Exception(e.getMessage()), HttpStatus.FORBIDDEN);
//    }
}
