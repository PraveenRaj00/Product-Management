package com.example.Ordermicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiResponse> handlingOrderNotFoundException(OrderNotFoundException exception){

        String msg= exception.getMessage();

        ApiResponse response= ApiResponse.builder()
                .message(msg)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
