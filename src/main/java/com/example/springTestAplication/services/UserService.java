package com.example.springTestAplication.services;


import com.example.springTestAplication.dto.user.AuthDTO;

import java.util.List;

public interface UserService {
//    UserDTO save(RegistrationDTO userDTO);
    List<AuthDTO> findAll();
    AuthDTO findByLogin(String login);
    AuthDTO update(String login, AuthDTO authDTO);
    String delete(String login);
}
