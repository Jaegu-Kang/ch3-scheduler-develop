package com.ch3schedulerdevelop.user.service;

import com.ch3schedulerdevelop.config.PasswordEncoder;
import com.ch3schedulerdevelop.exception.EmailNotFoundException;
import com.ch3schedulerdevelop.exception.InvalidPasswordException;
import com.ch3schedulerdevelop.exception.UnauthorizedAccessException;
import com.ch3schedulerdevelop.exception.UserNotFoundException;
import com.ch3schedulerdevelop.user.dto.*;
import com.ch3schedulerdevelop.user.entity.User;
import com.ch3schedulerdevelop.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateUserResponse saveUser(CreateUserRequest request){
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(
                request.getName(),
                request.getEmail(),
                encodedPassword
        );
        User savedUser = userRepository.save(user);
        return CreateUserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public SessionUser login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new EmailNotFoundException("존재하지 않는 이메일입니다.")
        );
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return SessionUser.from(user);
    }

    @Transactional(readOnly = true)
    public UserListResponse getAllUser(){
        List<User> users = userRepository.findAll();

        List<GetAllUserResponse> list = users.stream()
                .map(GetAllUserResponse::from)
                .toList();
        return UserListResponse.of(list);
    }

    @Transactional(readOnly = true)
    public GetAllUserResponse getOneUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
        return GetAllUserResponse.from(user);
    }

    @Transactional
    public UpdateUserResponse updateUser(Long userId, Long sessionUserId, UpdateUserRequest request)
        {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
        if (!user.getId().equals(sessionUserId)){
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
        user.update(
                request.getName(),
                request.getEmail()
        );
        return UpdateUserResponse.from(user);
    }
    @Transactional
    public void deleteUser(Long userId, long sessionUserId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
        if (!user.getId().equals(sessionUserId)){
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
        userRepository.delete(user);
    }
}
