package com.example.NewFriends.services.impl;


import com.example.NewFriends.dto.userData.CategoryDTO;
import com.example.NewFriends.dto.userData.UserDataCreateDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.LastFind;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.entity.UserData;
import com.example.NewFriends.repositories.FriendsRepository;
import com.example.NewFriends.repositories.LastFindRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDataServiceImpl implements UserDataService {

    private final UserDataRepository userDataRepository;
    private final UserDataMapper userDataMapper;
    private final FriendsRepository friendsRepository;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final LastFindRepository lastFindRepository;
    @Autowired
    public UserDataServiceImpl(UserDataRepository userDataRepository, UserRepository userRepository, UserDataMapper userDataMapper, FriendsRepository friendsRepository, JWTService jwtService, LastFindRepository lastFindRepository) {
        this.userDataRepository = userDataRepository;
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
        this.friendsRepository = friendsRepository;
        this.jwtService = jwtService;
        this.lastFindRepository = lastFindRepository;
    }


    @Override
    @Transactional
    public UserDataDTO findUser(HttpServletRequest request) {
        String username = jwtService.getLogin(request);
        UserData userData = userDataRepository.findDefaultUser(username);
        lastFindRepository.updateLastFoundUser(username, userData.getLogin());


        return userDataMapper.toDto(userData);
    }

    @Override
    @Transactional
    public void addNewFriend(HttpServletRequest request, String login) {
        String username = jwtService.getLogin(request);
        friendsRepository.insertNewFriend(username, login);
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
    @Transactional
    public UserDataDTO save(HttpServletRequest request, UserDataCreateDTO dto) {
        String login = jwtService.getLogin(request);

        UserData userData = new UserData();
        userData.setLogin(login);
        userData.setName(dto.getName());
        userData.setBirthday(dto.getBirthday());
        userData.setImage(dto.getImage());
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



        LastFind lastFind = new LastFind();
        lastFind.setLogin(user.getLogin());
        lastFind.setLastFindUser(user.getLogin());
        lastFindRepository.saveAndFlush(lastFind);
        return  userDataMapper.toDto(user);
    }

    @Override
    public UserDataDTO update(HttpServletRequest request, UserDataCreateDTO dto) {
        String login = jwtService.getLogin(request);

        UserData userData = new UserData();
        userData.setLogin(login);
        userData.setName(dto.getName());
        userData.setBirthday(dto.getBirthday());
        userData.setImage(dto.getImage());
        userData.setCity(dto.getCity());
        userData.setDescription(dto.getDescription());
        userData.setZodiacSign(ZodiacSign.getZodiacSign(dto.getBirthday()));
        userData.setSex(Sex.valueOf(dto.getSex()));
        UserData user = userDataRepository.save(userData);

        User user1 = userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User not found"));
        user1.setStatus(Status.ROLE_WAITING);
        userRepository.save(user1);
        return  userDataMapper.toDto(user);
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
