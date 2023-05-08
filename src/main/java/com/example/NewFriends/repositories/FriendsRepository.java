package com.example.NewFriends.repositories;



import com.example.NewFriends.entity.Friends;
import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    List<Friends> findAllByFriend1(UserData user);
    List<Friends> findAllByFriend2(UserData user);
}
