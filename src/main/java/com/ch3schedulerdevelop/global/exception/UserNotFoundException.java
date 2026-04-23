package com.ch3schedulerdevelop.global.exception;

import org.springframework.http.HttpStatus;

// 유저 조회 시 해당 식별자의 유저가 없을 때 발생하는 예외입니다.
public class UserNotFoundException extends ServerException {

    // 유저를 찾을 수 없으므로 404(NOT_FOUND) 상태 코드를 부모에게 전달합니다.
    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}