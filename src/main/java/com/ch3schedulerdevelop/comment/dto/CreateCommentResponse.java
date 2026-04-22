package com.ch3schedulerdevelop.comment.dto;

import com.ch3schedulerdevelop.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id","userId","title","content", "createdAt", "modifiedAt"})
@Getter
public class CreateCommentResponse {

    private final Long id;
    private final Long userId;
    private final Long schedulerId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private CreateCommentResponse(Long id, Long userId, Long schedulerId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.schedulerId = schedulerId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CreateCommentResponse from(Comment comment){
        return new CreateCommentResponse(
                comment.getId(),
                comment.getUser().getId(),
                comment.getScheduler().getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
