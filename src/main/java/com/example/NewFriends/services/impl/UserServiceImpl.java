package com.example.NewFriends.services.impl;


import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.services.UserService;
import com.example.NewFriends.services.mapper.AuthMapper;
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
    private final AuthMapper authMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthMapper authMapper) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow(()->new NoSuchElementException("User not found!"));
    }


    @Override
    @Transactional(readOnly = true)
    public List<AuthDTO> findAll() {
        return authMapper.toDtoList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthDTO findByLogin(String login) {
        return authMapper.toDto(userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User " + login + " not found")));
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User " + login + " not found"));
    }

    @Override
    @Transactional
    public AuthDTO update(String login, User updatedUser) {
        User user = userRepository.findById(login).orElseThrow(()->new NoSuchElementException("User " + login + " not found"));
        user.setLogin(updatedUser.getLogin());
        user.setPassword(updatedUser.getPassword());
        user.setStatus(updatedUser.getStatus());
        return authMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public String delete(String login) {
        userRepository.deleteById(login);
        return "User was successful deleted!";
    }


}
