package com.example.NewFriends.services;

import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.RegistrationDTO;
import com.example.NewFriends.dto.Authentication.AuthenticateDTO;
import com.example.NewFriends.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface AuthenticationService {

    AuthenticateDTO register(RegistrationDTO registration);
    AuthenticateDTO authenticate(AuthDTO auth);
    void saveUserToken(User user, String jwt);
    void revokeAllUserTokens(User user);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
