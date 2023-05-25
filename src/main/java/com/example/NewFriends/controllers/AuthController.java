package com.example.NewFriends.controllers;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.Authentication.AuthenticateDTO;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/registration")
    public ResponseEntity<AuthenticateDTO> register(@RequestBody UserDTO reg){
        return ResponseEntity.ok(authenticationService.register(reg));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateDTO> login(@RequestBody AuthDTO authDTO, HttpServletResponse response){
        return ResponseEntity.ok(authenticationService.authenticate(authDTO,response));
    }

    @GetMapping("/login")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request,response);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserDTO> showUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.getUserInfo(request));
    }
}
