package com.example.springTestAplication.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class AuthDTO {
    private final String login;
    private final String password;
    private final String status;
}
