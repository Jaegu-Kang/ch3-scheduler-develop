package com.ch3schedulerdevelop.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServerException {
    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}