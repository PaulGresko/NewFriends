package com.example.NewFriends.repositories;



import com.example.NewFriends.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
}
