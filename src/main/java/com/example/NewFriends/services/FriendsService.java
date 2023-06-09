package com.example.NewFriends.services;




import com.example.NewFriends.dto.friends.FriendsCreateDTO;
import com.example.NewFriends.dto.friends.FriendsDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface FriendsService {


    List<FriendsDTO> findAll();
    List<UserDataDTO> findAllFriends(HttpServletRequest request);
    List<UserDataDTO> findAllInvites(HttpServletRequest request);
    List<UserDataDTO> findAllRequests(HttpServletRequest request);
    FriendsDTO friendRequest(HttpServletRequest request, String login);

    void acceptRequest(HttpServletRequest request, String login2);
    void cancelRequest(HttpServletRequest request,String login2);

    FriendsDTO update(Long id, FriendsCreateDTO friendsDTO);
    String delete(Long id);
}
