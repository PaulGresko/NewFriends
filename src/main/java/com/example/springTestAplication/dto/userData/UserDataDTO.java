package com.example.springTestAplication.dto.userData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class UserDataDTO {

    private final String login;
    private final String name;
    private final String image;
    private final String sex;
    private final String description;
    private final Date birthday;
    private final String city;
    private final String zodiacSign;

}
