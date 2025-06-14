package com.retailx.CommerceEngine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ie){
        return ResponseEntity.badRequest().body(ie.getMessage());
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity handleCustomerNotFoundException(CustomerNotFoundException ce){
        return new ResponseEntity(ce.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException pe){
        return new ResponseEntity(pe.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity handleProductOutOfStockException(ProductOutOfStockException se){
        return new ResponseEntity(se.getMessage(), HttpStatus.CONFLICT);    //
    }
}
