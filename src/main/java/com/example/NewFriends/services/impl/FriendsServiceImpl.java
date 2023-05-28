package com.example.NewFriends.services.impl;

import com.example.NewFriends.dto.friends.FriendsCreateDTO;
import com.example.NewFriends.dto.friends.FriendsDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.Friends;
import com.example.NewFriends.repositories.FriendsRepository;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.security.JWTService;
import com.example.NewFriends.services.FriendsService;
import com.example.NewFriends.services.mapper.FriendsMapper;
import com.example.NewFriends.services.mapper.UserDataMapper;
import com.example.NewFriends.util.enums.FriendsStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FriendsServiceImpl implements FriendsService {


    private final FriendsRepository friendsRepository;
    private final UserDataRepository userDataRepository;
    private final FriendsMapper friendsMapper;
    private final UserDataMapper userDataMapper;
    private final JWTService jwtService;

    @Autowired
    public FriendsServiceImpl(FriendsRepository friendsRepository, UserDataRepository userDataRepository, FriendsMapper friendsMapper, UserDataMapper userDataMapper, JWTService jwtService) {
        this.friendsRepository = friendsRepository;
        this.userDataRepository = userDataRepository;
        this.friendsMapper = friendsMapper;
        this.userDataMapper = userDataMapper;
        this.jwtService = jwtService;
    }


    @Override
    public List<FriendsDTO> findAll() {
        return friendsMapper.toDtoList(friendsRepository.findAll());
    }

    @Override
    public List<UserDataDTO> findAllFriends(HttpServletRequest request) {
        String login = jwtService.getLogin(request);
        return userDataMapper.toDtoList(userDataRepository.findAllFriends(login));
    }

    @Override
    public List<UserDataDTO> findAllInvites(HttpServletRequest request) {
        String login = jwtService.getLogin(request);
        return userDataMapper.toDtoList(userDataRepository.findAllInvites(login));
    }

    @Override
    public List<UserDataDTO> findAllRequests(HttpServletRequest request){
        String login = jwtService.getLogin(request);
        return userDataMapper.toDtoList(userDataRepository.findAllRequests(login));
    }

    @Override
    public FriendsDTO friendRequest(HttpServletRequest request, String login) {
        Friends friends = new Friends();
        String username = jwtService.getLogin(request);

        friends.setFriend1(userDataRepository.findById(username).orElseThrow(()-> new NoSuchElementException("friend1 not found")));
        friends.setFriend2(userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("friend2 not found")));
        friends.setStatus(FriendsStatus.waiting);

        return friendsMapper.toDto(friendsRepository.save(friends));
    }

    @Override
    @Transactional
    public void acceptRequest(HttpServletRequest request, String login2) {
        String login1 = jwtService.getLogin(request);
        friendsRepository.acceptRequestFriend(login1,login2);
    }

    @Override
    @Transactional
    public void cancelRequest(HttpServletRequest request, String login2) {
        String login1 = jwtService.getLogin(request);
        friendsRepository.cancelRequestFriend(login1,login2);
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
