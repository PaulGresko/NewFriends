package com.example.NewFriends.services;



import com.example.NewFriends.dto.userData.UserDataDTO;

import java.util.List;



public interface UserDataService {
    List<UserDataDTO> findAll();
    UserDataDTO findByLogin(String login);
    List<UserDataDTO> findByCategory(String description, String sex, String zodiacSign,String city);
    UserDataDTO save(UserDataDTO userData);
    UserDataDTO update(String login, UserDataDTO userData);
    String delete(String login);


}
