package com.example.NewFriends.services.impl;

import com.example.NewFriends.dto.Message.MessageCreateDTO;
import com.example.NewFriends.dto.Message.MessageDTO;
import com.example.NewFriends.entity.Message;
import com.example.NewFriends.repositories.MessageRepository;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.security.JWTService;
import com.example.NewFriends.services.MessageService;
import com.example.NewFriends.services.mapper.MessageMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    
    private final MessageRepository messageRepository;
    private final UserDataRepository userDataRepository;
    private final MessageMapper messageMapper;
    private final JWTService jwtService;
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper, UserDataRepository userDataRepository, JWTService jwtService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userDataRepository = userDataRepository;
        this.jwtService = jwtService;
    }

    @Override
    public List<MessageDTO> findAllChats(HttpServletRequest request) {

        String username = jwtService.getLogin(request);
        return messageMapper.toDtoList(messageRepository.findAllChats(username));
    }

    @Override
    public List<MessageDTO> findAllMessages(HttpServletRequest request, String user2) {
        String user1 = jwtService.getLogin(request);

        return messageMapper.toDtoList(messageRepository.findAllMessages(user1,user2));
    }


    @Override
    @Transactional
    public MessageDTO save(MessageCreateDTO messageDTO) {

        messageRepository.updateLastMessage(messageDTO.getSender(),messageDTO.getRecipient());


        Message message = new Message();
        message.setSender(userDataRepository.findById(messageDTO.getSender())
                .orElseThrow(()->new NoSuchElementException("Отправитель не найден")));
        message.setRecipient(userDataRepository.findById(messageDTO.getRecipient())
                .orElseThrow(()->new NoSuchElementException("Получатель не найден")));
        message.setText(messageDTO.getText());
        message.setDate(new Date());
        message.setTime(new Date());
        message.setLast(true);
        return messageMapper.toDto(messageRepository.save(message));
    }

    @Override
    @Transactional
    public String delete(Long id) {
        messageRepository.deleteById(id);
        return "User was successful deleted!";
    }

    @Override
    @Transactional
    public MessageDTO update(Long id, MessageCreateDTO messageDTO) {
        Message message = messageRepository.findById(id).orElseThrow(()->new NoSuchElementException("Message not found"));
        message.setText(messageDTO.getText());
        return messageMapper.toDto(messageRepository.save(message));
    }
}
