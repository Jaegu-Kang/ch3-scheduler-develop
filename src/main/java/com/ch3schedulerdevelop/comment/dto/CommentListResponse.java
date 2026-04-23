package com.ch3schedulerdevelop.comment.dto;

import lombok.Getter;

import java.util.List;

// 내부 값을 외부에서 읽어 갈수있도록 게터 메서드를 자동으로 생성
@Getter
public class CommentListResponse {

    // 외부에서 직접 접근하지 못하도록 private 할당된 값을 바꿀 수 없도록 final로 선언하여 불변성을 보장
    private final List<GetAllCommentResponse> commentList;
    // 외부에서 객체 생성을 막고 내부 로직으로만 객체가 생성되도록 캡슐화합니다.
    private CommentListResponse(List<GetAllCommentResponse> commentList){
        this.commentList = commentList;
    }
    // 정적 팩토리 메서드로 객체를 생성합니다.
    public static CommentListResponse of(List<GetAllCommentResponse> commentList) {
        return new CommentListResponse(commentList);
    }
}
