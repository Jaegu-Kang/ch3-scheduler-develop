package com.ch3schedulerdevelop.comment.controller;

import com.ch3schedulerdevelop.comment.dto.CommentListResponse;
import com.ch3schedulerdevelop.comment.dto.CreateCommentRequest;
import com.ch3schedulerdevelop.comment.dto.CreateCommentResponse;
import com.ch3schedulerdevelop.comment.service.CommentService;
import com.ch3schedulerdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 이 클래스가 API 요청을 처리하는 컨트롤러임을 스프링에게 알립니다.
@RestController
// 생성자를 자동으로 만들어 줍니다.
@RequiredArgsConstructor
// 이 컨트롤러 내부의 모든 엔드포인트에 공통으로 경로를 붙여줍니다.
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;
    // 댓글 생성
    @PostMapping
    public ResponseEntity<CreateCommentResponse> saveComment(
            // 클라이언트의 HTTP 세션에서 저장된 객체를 찾아 변수에 넣어줍니다.
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser,
            // 클라이언트가 보낸 바디 데이터를 DTO로 변환해서 유효성 검사를 수행합니다.
            @Valid @RequestBody CreateCommentRequest request){
        // 세션유저가 null이라면 로그인한 사용자가 아니므로 접근을 차단합니다.
        if (sessionUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 세션에서 꺼낸 유저id와 클라이언트 요청데이터를 서비스로 보냅니다.
        CreateCommentResponse result = commentService.saveComment(sessionUser.getId(),request);
        //성공 상태코드와 결과물을 반환합니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<CommentListResponse> getAllComment(){
        // 서비스에서 모든댓글을 가져온 결과를 받습니다.
        CommentListResponse result = commentService.getAllCommentResponse();
        // 성공 상태 코드와 결과물을 반환합니다.
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
