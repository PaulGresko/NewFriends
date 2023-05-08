package com.example.NewFriends.services.mapper;


import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.UserData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataMapper {

    public UserDataDTO toDto(UserData userData){
        return new UserDataDTO(userData.getLogin(),
                userData.getName(),
                userData.getImage(),
                userData.getSex(),
                userData.getDescription(),
                userData.getBirthday(), userData.getCity(), userData.getZodiacSign());
    }
    public List<UserDataDTO> toDtoList(List<UserData> userData){
        return userData.stream().map(this::toDto).collect(Collectors.toList());
    }
}
