package com.ch3schedulerdevelop.comment.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
// 내부 값을 외부에서 읽어 갈수있도록 게터 메서드를 자동으로 생성
@Getter
public class CreateCommentRequest {

    // 클라이언트가 일정제목을 공백으로 할수없게 제한합니다.
    @NotNull
    private Long schedulerId;
    // 내용이 없거나 빈문자열이거나 공백임을 방지하고 에러시 메세지를 출력합니다.
    @NotBlank(message = "내용을 입력하세요.")
    // 내용의 길이를 제한하고 에러시 메세지를 출력합니다.
    @Size(max = 15, message = "내용은 15자 이하로 입력가능합니다.")
    private String content;
}
