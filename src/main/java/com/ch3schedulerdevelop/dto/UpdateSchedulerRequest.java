package com.ch3schedulerdevelop.dto;

import lombok.Getter;

@Getter
public class UpdateSchedulerRequest {

    private String name;
    private String title;
    private String content;
    private String password;
}
