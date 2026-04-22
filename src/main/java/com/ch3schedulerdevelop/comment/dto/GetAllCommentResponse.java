package com.ch3schedulerdevelop.comment.dto;

import com.ch3schedulerdevelop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllCommentResponse {

    private final Long id;
    private final Long userId;
    private final Long schedulerId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetAllCommentResponse(Long id, Long userId, Long schedulerId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.schedulerId = schedulerId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static GetAllCommentResponse from(Comment comment){
        return new GetAllCommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getScheduler().getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
