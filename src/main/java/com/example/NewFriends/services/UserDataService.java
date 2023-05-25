package com.example.NewFriends.services;



import com.example.NewFriends.dto.userData.CategoryDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;



public interface UserDataService {
    List<UserDataDTO> findAll(HttpServletRequest request);

    UserDataDTO findMyData(HttpServletRequest request);
    UserDataDTO findByLogin(String login);
//    List<UserDataDTO> findByCategory(String description, String sex, String zodiacSign,String city, Integer age1, Integer age2);
    List<UserDataDTO> findByCategory(CategoryDTO categoryDTO);
    UserDataDTO save(UserDataDTO userData);
    UserDataDTO update(String login, UserDataDTO userData);
    List<UserDataDTO> findUnverifiedUsers();
    String delete(String login);


}
