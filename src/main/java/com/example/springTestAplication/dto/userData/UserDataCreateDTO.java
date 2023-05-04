package com.example.springTestAplication.dto.userData;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserDataCreateDTO {

    private final String login;
    private final String name;
    private final String image;
    private final String sex;
    private final String description;
    private final Date birthday;
    private final String city;
    private final String zodiacSign;

    public UserDataCreateDTO(@JsonProperty("login") String login,
                             @JsonProperty("name") String name,
                             @JsonProperty("image") String image,
                             @JsonProperty("sex") String sex,
                             @JsonProperty("description") String description,
                             @JsonProperty("birthday") Date birthday,
                             @JsonProperty("city") String city,
                             @JsonProperty("zodiacSign")  String zodiacSign) {
        this.login = login;
        this.name = name;
        this.image = image;
        this.sex = sex;
        this.description = description;
        this.birthday = birthday;
        this.city = city;
        this.zodiacSign = zodiacSign;
    }
}
