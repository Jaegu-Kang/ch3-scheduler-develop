package com.ch3schedulerdevelop.global.exception;

import org.springframework.http.HttpStatus;

// 이메일은 맞지만 비밀번호가 틀렸을 때 발생하는 예외입니다.
public class InvalidPasswordException extends ServerException {

    // 인증 실패를 의미하는 401(UNAUTHORIZED) 상태 코드를 부모에게 전달합니다.
    public InvalidPasswordException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
