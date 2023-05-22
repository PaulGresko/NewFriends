package com.example.NewFriends.services.mapper;



import com.example.NewFriends.dto.Message.MessageDTO;
import com.example.NewFriends.entity.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {
    public MessageDTO toDto(Message message){
        return new MessageDTO(
                message.getSender().getLogin(),
                message.getRecipient().getLogin(),
                message.getDate(),
                message.getTime(),
                message.getText());
    }
    public List<MessageDTO> toDtoList(List<Message> messages){
        return messages.stream().map(this::toDto).collect(Collectors.toList());
    }
}
