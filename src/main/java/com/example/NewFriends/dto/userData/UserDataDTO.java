package com.example.NewFriends.dto.userData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class UserDataDTO {

    private final String login;
    private final String name;
    private final byte[] image;
    private final String sex;
    private final String description;
    private final Integer year;
    private final String city;
    private final String zodiacSign;

}
