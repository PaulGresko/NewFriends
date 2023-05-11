package com.example.NewFriends.services.impl;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.enums.Status;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.services.UserService;
import com.example.NewFriends.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findById(username).orElseThrow(()->new NoSuchElementException("User not found!"));
        //UserDetails usersDetails = User.withUsername(user.getLogin()).password(user.getPassword()).authorities(user.getStatus().name()).build();

        return user;
    }
//    @Override
//    @Transactional
//    public UserDTO save(RegistrationDTO userDTO) {
//        Users user = new Users();
//        user.setLogin(userDTO.getLogin());
//        user.setPassword(userDTO.getPassword());
//        user.setStatus(Status.valueOf(userDTO.getStatus()));
//        return userMapper.toDto(userRepository.save(user));
//    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthDTO> findAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthDTO findByLogin(String login) {
        return userMapper.toDto(userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User " + login + "not found")));
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User " + login + "not found"));
    }

    @Override
    @Transactional
    public AuthDTO update(String login, AuthDTO authDTO) {
        User user = userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User " + login + "not found"));
        user.setLogin(authDTO.getLogin());
        user.setPassword(authDTO.getPassword());
        user.setStatus(Status.valueOf(authDTO.getStatus()));
        return userMapper.toDto(userRepository.saveAndFlush(user));
    }

    @Override
    @Transactional
    public String delete(String login) {
        userRepository.deleteById(login);
        return "User was successful deleted!";
    }


}
