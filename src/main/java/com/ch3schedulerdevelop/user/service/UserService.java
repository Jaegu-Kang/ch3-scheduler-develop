package com.ch3schedulerdevelop.user.service;

import com.ch3schedulerdevelop.user.dto.CreateUserRequest;
import com.ch3schedulerdevelop.user.dto.CreateUserResponse;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse saveUser(CreateUserRequest request){
        User user = new User(
                request.getName(),
                request.getEmail()
        );
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }
}
