package com.ch3schedulerdevelop.user.dto;


import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponse {

    private final List<GetAllUserResponse> userList;

    private UserListResponse(List<GetAllUserResponse> userList) {
        this.userList = userList;
    }

    public static UserListResponse of(List<GetAllUserResponse> userList){
        return new UserListResponse(userList);
    }
}
