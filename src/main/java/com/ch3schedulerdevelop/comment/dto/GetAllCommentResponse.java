package com.ch3schedulerdevelop.comment.dto;

import com.ch3schedulerdevelop.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
// 내부 값을 외부에서 읽어 갈수있도록 게터 메서드를 자동으로 생성
@Getter
public class GetAllCommentResponse {

    // 외부에서 직접 접근하지 못하도록 private 할당된 값을 바꿀 수 없도록 final로 선언하여 불변성을 보장
    private final Long id;
    private final Long userId;
    private final Long schedulerId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 외부에서 객체 생성을 막고 내부 로직으로만 객체가 생성되도록 캡슐화합니다.
    private GetAllCommentResponse(Long id, Long userId, Long schedulerId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.schedulerId = schedulerId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // 정적 팩토리 메서드로 객체를 생성합니다.
    // 유저 전체를 받지 않고 매핑하여 필요한 데이터만 가져옵니다.
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
