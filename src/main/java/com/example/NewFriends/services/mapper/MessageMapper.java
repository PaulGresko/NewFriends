package com.example.NewFriends.services.mapper;



import com.example.NewFriends.dto.Message.MessageDTO;
import com.example.NewFriends.entity.Message;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {
    public MessageDTO toDto(Message message, String login){


        return new MessageDTO(
                message.getSender().getLogin().equals(login),
                message.getText(),
        DateFormat.getDateInstance().format(message.getDate())+ " " + DateFormat.getTimeInstance().format(message.getTime())
                );
    }
    public List<MessageDTO> toDtoList(List<Message> messages, String login){
        List<MessageDTO> list = new ArrayList<>();
        for(Message message : messages){
            list.add(toDto(message, login));
        }
        return list;
    }
}
