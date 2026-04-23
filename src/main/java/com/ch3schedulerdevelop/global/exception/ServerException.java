package com.ch3schedulerdevelop.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

//프로젝트에서 발생하는 모든 비즈니스 예외의 최상위 부모 클래스입니다.
// RuntimeException을 상속받아 트랜잭션 롤백이 가능하게 하며, 에러별로 다른 HTTP 상태 코드를 가질 수 있습니다.
public class ServerException extends RuntimeException {

    // 에러와 매칭될 HTTP 상태 코드(예: 404, 401 등)를 저장하는 필드입니다.
    private final HttpStatus status;

    public ServerException(HttpStatus status, String message) {
        // 부모 클래스(RuntimeException)의 생성자에 에러 메시지를 전달합니다.
        super(message);
        this.status = status;
    }
}