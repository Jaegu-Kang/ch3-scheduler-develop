package com.ch3schedulerdevelop.comment.dto;

import com.ch3schedulerdevelop.scheduler.entity.Scheduler;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotNull
    private Long schedulerId;
    @NotBlank(message = "내용을 입력하세요.")
    @Size(max = 15, message = "내용은 15자 이하로 입력가능합니다.")
    private String content;
}
