package com.example.NewFriends.dto.Authentication;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDTO {
    private final String login;
    private final String password;

}
