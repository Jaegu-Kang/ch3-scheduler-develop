package com.ch3schedulerdevelop.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends ServerException{
    public EmailNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
