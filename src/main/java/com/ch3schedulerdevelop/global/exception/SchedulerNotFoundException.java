package com.ch3schedulerdevelop.global.exception;

import org.springframework.http.HttpStatus;

// 요청한 일정 ID가 DB에 존재하지 않을 때 발생하는 예외입니다.
public class SchedulerNotFoundException extends ServerException {

    // 조회 실패를 의미하는 404(NOT_FOUND) 상태 코드를 부모에게 전달합니다.
    public SchedulerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
