package com.ch3schedulerdevelop.scheduler.dto;

import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"title","content","commentCount","createdAt", "modifiedAt", "userName"})
public class SchedulePageResponse {

    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String userName;

    private SchedulePageResponse(Scheduler scheduler, Long commentCount) {
        this.title = scheduler.getTitle();
        this.content = scheduler.getContent();
        this.commentCount = commentCount;
        this.createdAt = scheduler.getCreatedAt();
        this.modifiedAt = scheduler.getModifiedAt();
        this.userName = scheduler.getUser().getName();

    }

    public static SchedulePageResponse of(Scheduler scheduler, Long commentCount){
        return new SchedulePageResponse(scheduler, commentCount);
    }


}
