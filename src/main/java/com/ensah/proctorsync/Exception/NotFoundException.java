package com.ensah.proctorsync.Exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) this allows us to see the http status when we throw this exception
public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
