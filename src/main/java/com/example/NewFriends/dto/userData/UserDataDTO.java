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
    private final Date birthday;
    private final String city;
    private final String zodiacSign;
//    public UserDataDTO(@JsonProperty("login") String login,
//                             @JsonProperty("name") String name,
//                             @JsonProperty("image") String image,
//                             @JsonProperty("sex") String sex,
//                             @JsonProperty("description") String description,
//                             @JsonProperty("birthday") Date birthday,
//                             @JsonProperty("city") String city,
//                             @JsonProperty("zodiacSign")  String zodiacSign) {
//        this.login = login;
//        this.name = name;
//        this.image = image;
//        this.sex = sex;
//        this.description = description;
//        this.birthday = birthday;
//        this.city = city;
//        this.zodiacSign = zodiacSign;
//    }

}
