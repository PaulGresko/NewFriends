package com.example.springTestAplication.services.impl;

import com.example.springTestAplication.dto.friends.FriendsCreateDTO;
import com.example.springTestAplication.dto.friends.FriendsDTO;
import com.example.springTestAplication.entity.Friends;
import com.example.springTestAplication.entity.UserData;
import com.example.springTestAplication.repositories.FriendsRepository;
import com.example.springTestAplication.repositories.UserDataRepository;
import com.example.springTestAplication.services.FriendsService;
import com.example.springTestAplication.services.mapper.FriendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FriendsServiceImpl implements FriendsService {


    private final FriendsRepository friendsRepository;
    private final UserDataRepository userDataRepository;
    private final FriendsMapper friendsMapper;

    @Autowired
    public FriendsServiceImpl(FriendsRepository friendsRepository, UserDataRepository userDataRepository, FriendsMapper friendsMapper) {
        this.friendsRepository = friendsRepository;
        this.userDataRepository = userDataRepository;
        this.friendsMapper = friendsMapper;
    }


    @Override
    public List<FriendsDTO> findAll() {
        return friendsMapper.toDtoList(friendsRepository.findAll());
    }

    @Override
    public List<FriendsDTO> findByFriend1(String login) {
        UserData user = userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("friend1 not found"));
        return friendsMapper.toDtoList(friendsRepository.findAllByFriend1(user));
    }

    @Override
    public List<FriendsDTO> findByFriend2(String login) {
        UserData user = userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("friend2 not found"));
        return friendsMapper.toDtoList(friendsRepository.findAllByFriend2(user));
    }

    @Override
    public FriendsDTO save(FriendsCreateDTO friendsDTO) {
        Friends friends = new Friends();
        friends.setFriend1(userDataRepository.findById(friendsDTO.getFriend1()).orElseThrow(()-> new NoSuchElementException("friend1 not found")));
        friends.setFriend2(userDataRepository.findById(friendsDTO.getFriend2()).orElseThrow(()-> new NoSuchElementException("friend2 not found")));
        friends.setStatus(friendsDTO.getStatus());
        return friendsMapper.toDto(friendsRepository.save(friends));
    }

    @Override
    public FriendsDTO update(Long id, FriendsCreateDTO friendsDTO) {
        Friends friends = friendsRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Friends not found"));
        friends.setStatus(friends.getStatus());
        return friendsMapper.toDto(friendsRepository.save(friends));
    }

    @Override
    public String delete(Long id) {
        friendsRepository.deleteById(id);
        return "User was successful deleted!";
    }
}
