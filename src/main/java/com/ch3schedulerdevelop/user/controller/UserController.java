package com.ch3schedulerdevelop.user.controller;

import com.ch3schedulerdevelop.user.dto.*;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@Valid @RequestBody LoginRequest request, HttpSession session){
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<UserListResponse> getAllUser(){
        UserListResponse result = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetAllUserResponse> getOneUser(@PathVariable Long userId){
        GetAllUserResponse result = userService.getOneUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request){
        UpdateUserResponse result = userService.updateUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
