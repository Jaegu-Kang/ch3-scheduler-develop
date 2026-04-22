package com.ch3schedulerdevelop.comment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponse {

    private final List<GetAllCommentResponse> commentList;

    private CommentListResponse(List<GetAllCommentResponse> commentList){
        this.commentList = commentList;
    }

    public static CommentListResponse of(List<GetAllCommentResponse> commentList) {
        return new CommentListResponse(commentList);
    }
}
