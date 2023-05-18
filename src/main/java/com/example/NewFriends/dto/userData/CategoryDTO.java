package com.example.NewFriends.dto.userData;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class CategoryDTO {

    private final String description;
    private final String sex;
    private final String city;
    private final String zodiacSign;
    private final Integer age1;
    private final Integer age2;
}
