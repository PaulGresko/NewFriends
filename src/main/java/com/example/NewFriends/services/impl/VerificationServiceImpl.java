package com.example.NewFriends.services.impl;

import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.services.VerificationService;
import com.example.NewFriends.services.mapper.UserDataMapper;
import com.example.NewFriends.util.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final UserDataRepository userDataRepository;
    private final UserDataMapper userDataMapper;
    private final UserRepository userRepository;

    @Autowired
    public VerificationServiceImpl(UserDataRepository userDataRepository, UserDataMapper userDataMapper, UserRepository userRepository) {
        this.userDataRepository = userDataRepository;
        this.userDataMapper = userDataMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDataDTO> findUnverifiedUsers() {
        return userDataMapper.toDtoList(userDataRepository.findUnverifiedUsers());
    }

    @Override
    public UserDTO verify(String login) {
        User user = userRepository.findById(login).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setStatus(Status.ROLE_USER);
        user =userRepository.save(user);
        return new UserDTO(user.getLogin(),user.getStatus().name());
    }

    @Override
    public UserDTO unverify(String login) {
        User user = userRepository.findById(login).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setStatus(Status.ROLE_NEW);

        user = userRepository.save(user);
        userDataRepository.deleteById(login);

        return new UserDTO(user.getLogin(),user.getStatus().name());
    }
}
