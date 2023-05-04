package com.example.springTestAplication.repositories;


import com.example.springTestAplication.entity.Message;
import com.example.springTestAplication.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderAndRecipient(UserData sender, UserData recipient);
    List<Message> findAllBySender(UserData user);
    List<Message> findAllByRecipient(UserData user);
}
