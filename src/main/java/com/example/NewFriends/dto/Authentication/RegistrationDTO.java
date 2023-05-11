package com.example.NewFriends.dto.Authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegistrationDTO {

    private final String login;
    private final String password;

    public RegistrationDTO(@JsonProperty("login")    String login,
                           @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }
}
