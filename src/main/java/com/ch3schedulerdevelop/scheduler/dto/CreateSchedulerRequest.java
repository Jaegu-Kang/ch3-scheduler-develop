package com.ch3schedulerdevelop.scheduler.dto;

import lombok.Getter;

@Getter
public class CreateSchedulerRequest {

    private Long userId;
    private String title;
    private String content;
}
