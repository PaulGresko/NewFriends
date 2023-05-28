package com.example.NewFriends.services;



import com.example.NewFriends.dto.userData.CategoryDTO;
import com.example.NewFriends.dto.userData.UserDataCreateDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;



public interface UserDataService {
    UserDataDTO findUser(HttpServletRequest request);

    UserDataDTO findMyData(HttpServletRequest request);
    UserDataDTO findByLogin(String login);
    List<UserDataDTO> findByCategory(CategoryDTO categoryDTO);
    UserDataDTO save(HttpServletRequest request, UserDataCreateDTO userData);
    UserDataDTO update(HttpServletRequest request, UserDataCreateDTO userData);
    List<UserDataDTO> findUnverifiedUsers();
    String delete(String login);
}
