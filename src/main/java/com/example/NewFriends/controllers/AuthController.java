package com.example.NewFriends.controllers;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.RegistrationDTO;
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
public class AuthController {


    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/registration")
    public ResponseEntity<AuthenticateDTO> register(@RequestBody RegistrationDTO reg){
        return ResponseEntity.ok(authenticationService.register(reg));
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "/auth/admin";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateDTO> performLogin(@RequestBody AuthDTO authDTO){
        return ResponseEntity.ok(authenticationService.authenticate(authDTO));
    }

    @GetMapping("/login")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request,response);
    }


    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (User)authentication.getPrincipal();
        return userDetails.getAuthorities().stream().toString() + "\n" + userDetails.getUsername() + "\n" + userDetails.getPassword();
    }
}
