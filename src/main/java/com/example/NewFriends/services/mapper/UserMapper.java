package com.example.NewFriends.services.mapper;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.Authentication.RegistrationDTO;
import com.example.NewFriends.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public AuthDTO toDto(User user){
       return new AuthDTO(user.getLogin(), user.getPassword(), user.getStatus().name());
    }

    public List<AuthDTO> toDtoList(List<User> users){
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User fromDto(RegistrationDTO reg){
        User user = new User();
        user.setLogin(reg.getLogin());
        user.setPassword(reg.getPassword());
        return user;
    }
}
