package com.example.NewFriends.services;



import com.example.NewFriends.dto.Message.ChatDTO;
import com.example.NewFriends.dto.Message.MessageCreateDTO;
import com.example.NewFriends.dto.Message.MessageDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface MessageService {

    List<ChatDTO> findAllChats(HttpServletRequest request);
    List<MessageDTO> findAllMessages(HttpServletRequest request, String user2);
    MessageDTO save(MessageCreateDTO messageDTO);
    String delete(Long id);
    MessageDTO update(Long id, MessageCreateDTO messageDTO);
}
