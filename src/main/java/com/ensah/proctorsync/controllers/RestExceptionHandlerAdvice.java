package com.ensah.proctorsync.controllers;

import com.ensah.proctorsync.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class RestExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        e.getMessage()
                );
        problemDetail.setTitle("Something Not Found");
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
        problemDetail.setProperty("custom_property", "Not found message");
        return problemDetail;
    }
}