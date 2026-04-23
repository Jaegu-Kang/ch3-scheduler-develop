package com.ch3schedulerdevelop.global.exception;

import org.springframework.http.HttpStatus;

// 본인의 게시물이 아닌데 수정/삭제를 시도하는 등 권한이 없을 때 발생하는 예외입니다.
public class UnauthorizedAccessException extends ServerException {

    // 접근 거부(권한 없음)를 의미하는 403(FORBIDDEN) 상태 코드를 부모에게 전달합니다.
    public UnauthorizedAccessException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}