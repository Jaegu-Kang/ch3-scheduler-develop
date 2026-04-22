package com.ch3schedulerdevelop.comment.controller;

import com.ch3schedulerdevelop.comment.dto.CreateCommentRequest;
import com.ch3schedulerdevelop.comment.dto.CreateCommentResponse;
import com.ch3schedulerdevelop.comment.service.CommentService;
import com.ch3schedulerdevelop.user.dto.SessionUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> saveComment(
            @SessionAttribute(name = "LoginUser", required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateCommentRequest request){
        if (sessionUser == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CreateCommentResponse result = commentService.saveComment(sessionUser.getId(),request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
