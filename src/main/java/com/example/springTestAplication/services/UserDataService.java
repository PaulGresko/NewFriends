package com.example.springTestAplication.services;



import com.example.springTestAplication.dto.userData.UserDataCreateDTO;
import com.example.springTestAplication.dto.userData.UserDataDTO;

import java.util.List;



public interface UserDataService {
    List<UserDataDTO> findAll();
    UserDataDTO findByLogin(String login);
    List<UserDataDTO> findByCategory(String description, String sex, String zodiacSign,String city);
    UserDataDTO save(UserDataCreateDTO userData);
    String delete(String login);


}
