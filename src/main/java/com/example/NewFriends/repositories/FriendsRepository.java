package com.example.NewFriends.repositories;



import com.example.NewFriends.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {

    @Modifying
    @Query(value = "insert into friends(friend1,friend2,status) values(:friend1, :friend2,'waiting');",
            nativeQuery = true)
    void insertNewFriend(String friend1, String friend2);


    @Modifying
    @Query(value = "update friends set status = 'friends'\n" +
            "    where friend1 = :login2 and friend2 = :login1",
            nativeQuery = true)
    void acceptRequestFriend(String login1, String login2);

    @Modifying
    @Query(value = "update friends set status = 'lock'\n" +
            "    where friend1 = :login2 and friend2 = :login1",
        nativeQuery = true)
    void cancelRequestFriend(String login1, String login2);
}
