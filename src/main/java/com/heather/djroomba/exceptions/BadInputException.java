package com.heather.djroomba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown if there are issues with the setup or directions
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadInputException extends RuntimeException {

    public BadInputException(String message) {
        super(message);
    }
}
