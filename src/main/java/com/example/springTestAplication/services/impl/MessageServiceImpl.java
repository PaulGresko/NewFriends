package com.example.springTestAplication.services.impl;

import com.example.springTestAplication.dto.Message.MessageCreateDTO;
import com.example.springTestAplication.dto.Message.MessageDTO;
import com.example.springTestAplication.entity.Message;
import com.example.springTestAplication.entity.UserData;
import com.example.springTestAplication.repositories.MessageRepository;
import com.example.springTestAplication.repositories.UserDataRepository;
import com.example.springTestAplication.services.MessageService;
import com.example.springTestAplication.services.mapper.MessageMapper;
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
    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper,UserDataRepository userDataRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userDataRepository = userDataRepository;
    }

    @Override
    public List<MessageDTO> findAll() {
        return messageMapper.toDtoList(messageRepository.findAll());
    }

    @Override
    public List<MessageDTO> findBySender(String login) {
        UserData user = userDataRepository.findById(login).orElseThrow(()->new NoSuchElementException("Message`s sender not found"));
        return messageMapper.toDtoList(messageRepository.findAllBySender(user));
    }

    @Override
    public List<MessageDTO> findByRecipient(String login) {
        UserData user = userDataRepository.findById(login).orElseThrow(()->new NoSuchElementException("Message`s sender not found"));
        return messageMapper.toDtoList(messageRepository.findAllByRecipient(user));
    }

    @Override
    @Transactional
    public MessageDTO save(MessageCreateDTO messageDTO) {
        Message message = new Message();
        message.setSender(userDataRepository.findById(messageDTO.getSender()).orElseThrow(()->new NoSuchElementException("Message`s sender not found")));
        message.setRecipient(userDataRepository.findById(messageDTO.getRecipient()).orElseThrow(()->new NoSuchElementException("Message`s recipient not found")));
        message.setText(messageDTO.getText());
        message.setDate(new Date());
        message.setTime(new Date());
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
