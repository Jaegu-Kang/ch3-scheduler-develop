package com.ch3schedulerdevelop.scheduler.dto;

import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"id","userId","title","content", "createdAt", "modifiedAt"})
public class CreateSchedulerResponse {

    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private CreateSchedulerResponse(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static CreateSchedulerResponse from(Scheduler scheduler){
        return new CreateSchedulerResponse(
                scheduler.getId(),
                scheduler.getUser().getId(),
                scheduler.getTitle(),
                scheduler.getContent(),
                scheduler.getCreatedAt(),
                scheduler.getModifiedAt()
        );
    }
}
