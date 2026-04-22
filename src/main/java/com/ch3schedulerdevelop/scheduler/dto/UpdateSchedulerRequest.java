package com.ch3schedulerdevelop.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateSchedulerRequest {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    @Size(max = 100, message = "내용은 100자 이하로 입력가능합니다.")
    private String content;
}
