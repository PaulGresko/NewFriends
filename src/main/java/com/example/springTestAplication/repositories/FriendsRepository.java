package com.example.springTestAplication.repositories;



import com.example.springTestAplication.entity.Friends;
import com.example.springTestAplication.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    List<Friends> findAllByFriend1(UserData user);
    List<Friends> findAllByFriend2(UserData user);
}
