package com.ch3schedulerdevelop.user.controller;

import com.ch3schedulerdevelop.user.dto.CreateUserRequest;
import com.ch3schedulerdevelop.user.dto.CreateUserResponse;
import com.ch3schedulerdevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponse> saveUser(@RequestBody CreateUserRequest request){
        CreateUserResponse result = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
