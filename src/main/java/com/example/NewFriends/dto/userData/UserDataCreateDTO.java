package com.example.NewFriends.dto.userData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor
public class UserDataCreateDTO {
    private final String name;
    private final byte[] image;
    private final String sex;
    private final String description;
    private final Date birthday;
    private final String city;
}
