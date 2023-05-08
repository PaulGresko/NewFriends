package com.example.NewFriends.services.impl;


import com.example.NewFriends.dto.userData.UserDataCreateDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.UserData;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.services.UserDataService;
import com.example.NewFriends.services.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;
    private final UserDataMapper userDataMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository,UserRepository userRepository, UserDataMapper userDataMapper) {
        this.userDataRepository = userDataRepository;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }


    @Override
    public List<UserDataDTO> findAll() {
        return userDataMapper.toDtoList(userDataRepository.findAll());
    }

    @Override
    public UserDataDTO findByLogin(String login) {
        return userDataMapper.toDto(userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("User not found")));
    }

    @Override
    public List<UserDataDTO> findByCategory(String description, String sex, String zodiacSign, String city) {
        return userDataMapper.toDtoList(userDataRepository.findByDescriptionLikeAndSexAndZodiacSignAndCity(description,sex,zodiacSign,city));
    }

    @Override
    public UserDataDTO save(UserDataCreateDTO userDataDTO) {
        UserData userData = new UserData();
        userData.setLogin(userDataDTO.getLogin());
        userData.setName(userDataDTO.getName());
        userData.setBirthday(userDataDTO.getBirthday());
        userData.setImage(userDataDTO.getImage());
        userData.setCity(userDataDTO.getCity());
        userData.setDescription(userDataDTO.getDescription());
        userData.setZodiacSign(userDataDTO.getZodiacSign());
        userData.setSex(userDataDTO.getSex());
        userData.setUser(userRepository.findById(userDataDTO.getLogin()).orElseThrow(()->new NoSuchElementException("User not found")));
        return  userDataMapper.toDto(userDataRepository.save(userData));
    }

    @Override
    public String delete(String login) {
        userDataRepository.deleteById(login);
        return "User was successful deleted!";
    }

}
