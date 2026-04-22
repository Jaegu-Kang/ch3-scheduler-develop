package com.ch3schedulerdevelop.exception;

import org.springframework.http.HttpStatus;

public class SchedulerNotFoundException extends ServerException {
    public SchedulerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
