package com.example.NewFriends.services.impl;

import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.RegistrationDTO;
import com.example.NewFriends.dto.Authentication.TokensDTO;
import com.example.NewFriends.entity.Token;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.enums.Status;
import com.example.NewFriends.repositories.TokenRepository;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.security.JWTService;
import com.example.NewFriends.services.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }


    @Override
    public TokensDTO register(RegistrationDTO registration) {
        User user = User.builder()
                .login(registration.getLogin())
                .password(passwordEncoder.encode(registration.getPassword()))
                .status(Status.ROLE_NEW)
                .build();
        User savedUser = userRepository.save(user);
        String jwt = jwtService.generateJWT(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwt);
        return new TokensDTO(jwt, refreshToken);
    }

    @Override
    public TokensDTO authenticate(AuthDTO auth) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    auth.getLogin(),
                    auth.getPassword()
        ));
        User user = userRepository.findById(auth.getLogin()).orElseThrow(()->new NoSuchElementException("User sender not found"));
        String jwt = jwtService.generateJWT(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user,jwt);
        return new TokensDTO(jwt, refreshToken);
    }

    @Override
    public void saveUserToken(User user, String jwt) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwt);
        token.setExpired(false);
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllByExpiredAndUser(false, user);
        if(validUserTokens.isEmpty()){
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader("RefreshToken");
        final String refreshToken;
        final String username;
        if(authHeader == null) {
            return;
        }
        refreshToken = authHeader;
        username = jwtService.extractLogin(refreshToken);
        if(username != null){
            User user = userRepository.findById(username)
                    .orElseThrow(()->new NoSuchElementException("User sender not found"));
            if(jwtService.isTokenValid(refreshToken,user)){
                String jwt = jwtService.generateJWT(user);
                revokeAllUserTokens(user);
                saveUserToken(user,jwt);
                new ObjectMapper().writeValue(response.getOutputStream(), new TokensDTO(jwt,refreshToken));
            }
        }
    }
}
