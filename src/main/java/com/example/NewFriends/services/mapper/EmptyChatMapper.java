package com.example.NewFriends.services.mapper;

import com.example.NewFriends.dto.Message.EmptyChatDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmptyChatMapper {
    public EmptyChatDTO toDto(Object[] chat){
        return new EmptyChatDTO(
                (String) chat[0],
                (String) chat[1],
                (byte[]) chat[2]
        );
    }

    public List<EmptyChatDTO> toDtoList(List<Object[]> chats){
        return chats.stream().map(this::toDto).collect(Collectors.toList());
    }
}
