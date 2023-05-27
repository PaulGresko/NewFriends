package com.example.NewFriends.services;

import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;

import java.util.List;

public interface VerificationService {


    List<UserDataDTO> findUnverifiedUsers();
    UserDTO verify(String login);
    UserDTO unverify(String login);
}
