package com.example.NewFriends.repositories;


import com.example.NewFriends.entity.Message;
import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "select * from (select name, image, m.*\n" +
            "               from user_data\n" +
            "                        right join message m on user_data.login = m.recipient\n" +
            "                where sender = :user and last = true\n" +
            "                ) a union\n" +
            "                (select name, image,  m.*\n" +
            "                from user_data\n" +
            "                    right join message m on user_data.login = m.sender\n" +
            "                where recipient = :user and last = true\n" +
            "                )\n" +
            "    order by date desc , time desc;",nativeQuery = true)
    List<Object[]> findAllChats(String user);


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
