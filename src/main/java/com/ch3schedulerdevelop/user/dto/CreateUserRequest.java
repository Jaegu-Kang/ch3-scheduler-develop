package com.ch3schedulerdevelop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotBlank
    @Size(max = 5, message = "5글자 이하로 입력해주세요.")
    private String name;
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
    @Size(min = 8, max = 15)
    private String password;
}
