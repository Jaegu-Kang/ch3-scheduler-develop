package com.ch3schedulerdevelop.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends ServerException {
    public InvalidPasswordException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
