package com.ch3schedulerdevelop.global.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends ServerException {
    public UnauthorizedAccessException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}