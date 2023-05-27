package com.example.NewFriends.services.impl;


import com.example.NewFriends.dto.userData.CategoryDTO;
import com.example.NewFriends.dto.userData.UserDataCreateDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.entity.UserData;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.security.JWTService;
import com.example.NewFriends.services.UserDataService;
import com.example.NewFriends.services.mapper.UserDataMapper;
import com.example.NewFriends.util.enums.Sex;
import com.example.NewFriends.util.enums.Status;
import com.example.NewFriends.util.enums.ZodiacSign;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;
    private final UserDataMapper userDataMapper;
    private final JWTService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository, UserRepository userRepository, UserDataMapper userDataMapper, JWTService jwtService) {
        this.userDataRepository = userDataRepository;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
        this.jwtService = jwtService;
    }


    @Override
    public List<UserDataDTO> findAll(HttpServletRequest request) {
        String username = jwtService.getLogin(request);
        return userDataMapper.toDtoList(userDataRepository.findDefaultUsers(username));
    }

    @Override
    public UserDataDTO findMyData(HttpServletRequest request) {
        String username = jwtService.getLogin(request);
        return  userDataMapper.toDto(userDataRepository.findMyData(username).orElse(
                UserData.builder()
                        .login(username)
                        .name("")
                        .image("".getBytes())
                        .sex(Sex.М)
                        .description("")
                        .birthday(new Date())
                        .city("")
                        .zodiacSign(ZodiacSign.Водолей)
                        .build()
        ));
    }

    @Override
    public UserDataDTO findByLogin(String login) {
        return userDataMapper.toDto(userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("User not found")));
    }

    @Override
    public List<UserDataDTO> findByCategory(CategoryDTO DTO) {
        List<UserData> users;
        users =  userDataRepository.findByCategory(
                DTO.getDescription(),
                DTO.getSex(),
                DTO.getZodiacSign(),
                DTO.getCity(),
                DTO.getAge1(),
                DTO.getAge2());
        return userDataMapper.toDtoList(users);
    }

    @Override
    public UserDataDTO save(MultipartFile file, HttpServletRequest request, UserDataCreateDTO dto) throws IOException {
        String login = jwtService.getLogin(request);

        UserData userData = new UserData();
        userData.setLogin(login);
        userData.setName(dto.getName());
        userData.setBirthday(dto.getBirthday());
        userData.setImage(file.getBytes());
        userData.setCity(dto.getCity());
        userData.setDescription(dto.getDescription());
        userData.setZodiacSign(ZodiacSign.getZodiacSign(dto.getBirthday()));
        userData.setSex(Sex.valueOf(dto.getSex()));
        //userData.setUser(userRepository.findById(dto.getLogin()).orElseThrow(()->new NoSuchElementException("User not found")));
        UserData user = userDataRepository.save(userData);

       // userRepository.updateUserStatusToWaiting(dto.getLogin());
        User user1 = userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User not found"));
        user1.setStatus(Status.ROLE_WAITING);
        userRepository.save(user1);
        return  userDataMapper.toDto(user);
    }

    @Override
    public UserDataDTO update(HttpServletRequest request, UserDataCreateDTO dto) {
//        String login = jwtService.getLogin(request);
//        UserData userData = UserData.builder()
//                .user(userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User not found")))
//                .birthday(dto.getBirthday())
//                .name(dto.getName())
//                .city(dto.getCity())
//                .image(dto.getImage())
//                .description(dto.getDescription())
//                .sex(Sex.valueOf(dto.getSex()))
//                .zodiacSign(ZodiacSign.getZodiacSign(dto.getBirthday()))
//                .login(login)
//                .build();

//        return userDataMapper.toDto(userDataRepository.save(userData));
  return null;
    }

    @Override
    public List<UserDataDTO> findUnverifiedUsers() {
        return userDataMapper.toDtoList(userDataRepository.findUnverifiedUsers());
    }

    @Override
    public String delete(String login) {
        userDataRepository.deleteById(login);
        return "User was successful deleted!";
    }

}
