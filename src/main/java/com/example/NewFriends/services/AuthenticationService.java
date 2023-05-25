package com.example.NewFriends.services;

import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.Authentication.AuthenticateDTO;
import com.example.NewFriends.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface AuthenticationService {

    AuthenticateDTO register(AuthDTO registration);
    AuthenticateDTO authenticate(AuthDTO auth, HttpServletResponse response);
    void saveUserToken(User user, String jwt);
    void revokeAllUserTokens(User user);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    UserDTO getUserInfo(HttpServletRequest request);
}
