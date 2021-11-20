package com.klm.cases.df.advice;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@ControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(HttpServerErrorException.class)
    public HttpEntity handleHttpServerErrorException(HttpServerErrorException e) {
        return new ResponseEntity(e.getStatusCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity handleBadRequest() {
        return new ResponseEntity(BAD_REQUEST);
    }
    
    
    @ExceptionHandler(Throwable.class)
    public HttpEntity handleException(Throwable t) {
        return new ResponseEntity(SERVICE_UNAVAILABLE);
    }

}
