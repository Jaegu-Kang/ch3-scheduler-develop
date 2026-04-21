package com.ch3schedulerdevelop.user.service;

import com.ch3schedulerdevelop.user.dto.*;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse saveUser(CreateUserRequest request){
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<GetAllUserResponse> getAllUser(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(GetAllUserResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public GetAllUserResponse getOneUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        return GetAllUserResponse.from(user);
    }

    @Transactional
    public UpdateUserResponse updateUser(Long userId, UpdateUserRequest request){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        user.update(
                request.getName(),
                request.getEmail()
        );
        return UpdateUserResponse.from(user);
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        userRepository.delete(user);
    }
}
