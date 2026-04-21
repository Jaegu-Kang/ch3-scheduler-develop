package com.ch3schedulerdevelop.user.dto;

import com.ch3schedulerdevelop.user.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {

    private final Long id;
    private final String email;
    private final String password;

    private SessionUser(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static SessionUser from(User user){
        return new SessionUser(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }

}
