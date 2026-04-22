package com.ch3schedulerdevelop.scheduler.dto;

import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponse {

    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String userName;

    public SchedulePageResponse(Scheduler scheduler, Long commentCount) {
        this.title = scheduler.getTitle();
        this.content = scheduler.getContent();
        this.commentCount = commentCount;
        this.createdAt = scheduler.getCreatedAt();
        this.modifiedAt = scheduler.getModifiedAt();
        this.userName = scheduler.getUser().getName();

    }


}
