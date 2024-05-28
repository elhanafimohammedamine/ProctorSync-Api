package com.ensah.proctorsync.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice // this allows this class to work across the entire application
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistException.class)
    public ResponseEntity<Object> UserAlreadyExistException(AlreadyExistException e) {
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e) {
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }

    // this method override from ResponseEntityExceptionHandler and it handles all the validation errors in the entire application
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ApiException apiException = new ApiException("Validation failed", ex, HttpStatus.BAD_REQUEST, ZonedDateTime.now(), errors);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
