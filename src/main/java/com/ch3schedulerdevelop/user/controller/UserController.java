package com.ch3schedulerdevelop.user.controller;

import com.ch3schedulerdevelop.user.dto.CreateUserRequest;
import com.ch3schedulerdevelop.user.dto.CreateUserResponse;
import com.ch3schedulerdevelop.user.dto.GetAllUserResponse;
import com.ch3schedulerdevelop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<GetAllUserResponse>> getAllUser(){
        List<GetAllUserResponse> result = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetAllUserResponse> getOneUser(@PathVariable Long userId){
        GetAllUserResponse result = userService.getOneUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
