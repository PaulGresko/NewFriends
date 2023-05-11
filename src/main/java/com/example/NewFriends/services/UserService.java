package com.example.NewFriends.services;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.entity.User;

import java.util.List;

public interface UserService {
//    UserDTO save(RegistrationDTO userDTO);
    List<AuthDTO> findAll();
    AuthDTO findByLogin(String login);

    User findUserByLogin(String login);

    AuthDTO update(String login, AuthDTO authDTO);
    String delete(String login);
}
