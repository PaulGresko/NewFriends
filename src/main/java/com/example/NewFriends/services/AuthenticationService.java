package com.example.NewFriends.services;

import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.RegistrationDTO;
import com.example.NewFriends.dto.Authentication.TokensDTO;
import com.example.NewFriends.entity.Token;
import com.example.NewFriends.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;


public interface AuthenticationService {

    TokensDTO register(RegistrationDTO registration);
    TokensDTO authenticate(AuthDTO auth);
    void saveUserToken(User user, String jwt);
    void revokeAllUserTokens(User user);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
