package com.example.NewFriends.services;



import com.example.NewFriends.dto.Message.MessageCreateDTO;
import com.example.NewFriends.dto.Message.MessageDTO;

import java.util.List;


public interface MessageService {

    List<MessageDTO> findAll();
    List<MessageDTO> findBySender(String login);
    List<MessageDTO> findByRecipient(String login);
    MessageDTO save(MessageCreateDTO messageDTO);
    String delete(Long id);
    MessageDTO update(Long id, MessageCreateDTO messageDTO);
}
