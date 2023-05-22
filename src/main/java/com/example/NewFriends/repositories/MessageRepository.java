package com.example.NewFriends.repositories;


import com.example.NewFriends.entity.Message;
import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select * from message where (recipient = :user or sender = :user) and last = true order by date desc, time desc;",nativeQuery = true)
    List<Message> findAllChats(String user);


    @Query(value = "select * from message where\n" +
            "        (recipient = :user1 and sender = :user2)\n" +
            "        or (recipient = :user2 and sender = :user1) order by date desc, time desc;",nativeQuery = true)
    List<Message> findAllMessages(String user1, String user2);

    @Query(value="update message set last = false\n" +
            "               where (recipient = :user1 and sender = :user2)\n" +
            "                  or (recipient = :user2 and sender = :user1)\n" +
            "                and last = true;",nativeQuery = true)
    @Modifying
    void updateLastMessage(String user1, String user2);
}
