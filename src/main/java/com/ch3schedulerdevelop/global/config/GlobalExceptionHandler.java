package com.ch3schedulerdevelop.global.config;

import com.ch3schedulerdevelop.global.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// 프로젝트 전역에서 발생하는 예외를 한곳에서 잡아 처리하는 공통 예외 처리기 임을 명시합니다.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외인 ServerException이 발생했을 때 이 메서드가 실행되도록 매핑합니다.
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> handleServerException(ServerException ex) {
        //예외 객체(ex)에 담긴 HTTP 상태 코드와 에러 메시지를 꺼내어 클라이언트에게 그대로 전달합니다.
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }

    // @Valid 검증에 실패했을 때 발생하는 MethodArgumentNotValidException을 처리합니다.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        // 어떤 필드에서 어떤 에러가 났는지 담기 위한 Map 객체를 생성합니다.
        Map<String, String> errors = new HashMap<>();
        // 발생한 모든 필드 에러를 순회하며 필드명(예: title)과 설정한 메시지(예: 10자 이내...)를 맵에 담습니다.
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        // 잘못된 요청임을 뜻하는 BAD_REQUEST 상태 코드와 함께 정리된 에러 상세 목록을 반환합니다.
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(errors);
    }
}