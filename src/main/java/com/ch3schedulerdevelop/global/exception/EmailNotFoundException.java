package com.ch3schedulerdevelop.global.exception;

import org.springframework.http.HttpStatus;

// 로그인 시 입력한 이메일이 DB에 없을 때 발생하는 예외입니다.
public class EmailNotFoundException extends ServerException{
    // 데이터가 없음을 의미하는 404(NOT_FOUND) 상태 코드를 부모에게 전달합니다.
    public EmailNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
