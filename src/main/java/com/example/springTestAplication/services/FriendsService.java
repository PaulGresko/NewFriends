package com.example.springTestAplication.services;




import com.example.springTestAplication.dto.friends.FriendsCreateDTO;
import com.example.springTestAplication.dto.friends.FriendsDTO;

import java.util.List;

public interface FriendsService {


    List<FriendsDTO> findAll();
    List<FriendsDTO> findByFriend1(String login);
    List<FriendsDTO> findByFriend2(String login);
    FriendsDTO save(FriendsCreateDTO friendsDTO);
    FriendsDTO update(Long id, FriendsCreateDTO friendsDTO);
    String delete(Long id);
}
