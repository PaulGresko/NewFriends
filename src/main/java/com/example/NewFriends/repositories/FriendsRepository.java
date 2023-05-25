package com.example.NewFriends.repositories;



import com.example.NewFriends.entity.Friends;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
}
