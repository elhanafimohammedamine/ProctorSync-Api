package com.ensah.proctorsync.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

// @Data // this comes from lombok
// and it used to creates AllArgsConstructor, getters, setter, toString, equals and hashCode
// if we use this annotation we don't need to write all of this code
@Getter
public class ApiException {
    private final String message;
    private final Map<String , String> errors;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.errors = new HashMap<>();
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime, Map<String , String> errors) {
        this.message = message;
        this.errors = errors;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                ", httpStatus=" + httpStatus +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }

}
