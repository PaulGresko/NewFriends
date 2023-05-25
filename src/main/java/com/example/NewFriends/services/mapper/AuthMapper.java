package com.example.NewFriends.services.mapper;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthMapper {
    public AuthDTO toDto(User user){
       return new AuthDTO(user.getLogin(), user.getPassword(),user.getStatus().name());
    }

    public List<AuthDTO> toDtoList(List<User> users){
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
