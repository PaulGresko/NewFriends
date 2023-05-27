package com.example.NewFriends.services.mapper;


import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.UserData;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataMapper {

    public UserDataDTO toDto(UserData userData){
        return new UserDataDTO(
                userData.getLogin(),
                userData.getName(),
                userData.getImage(),
                userData.getSex().name(),
                userData.getDescription(),
                calculateAge(userData.getBirthday()),
                userData.getCity(),
                userData.getZodiacSign().name());
    }
    public List<UserDataDTO> toDtoList(List<UserData> userData){
        return userData.stream().map(this::toDto).collect(Collectors.toList());
    }

    private static int calculateAge(Date birthDate) {
        Date currentDate = new Date();
        long diffInMillis = currentDate.getTime() - birthDate.getTime();
        long ageInMillis = 1000L * 60 * 60 * 24 * 365; // количество миллисекунд в году
        int age = (int) (diffInMillis / ageInMillis);
        return age;
    }
}
