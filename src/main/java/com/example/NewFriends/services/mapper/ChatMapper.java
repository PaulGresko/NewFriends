package com.example.NewFriends.services.mapper;

import com.example.NewFriends.dto.Message.ChatDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatMapper {
    public ChatDTO toDto(Object[] chat,String login){
        ChatDTO dto = new ChatDTO();
        dto.setCompanionName((String) chat[0]);
        dto.setImage((String) chat[1]);
        dto.setText((String) chat[7]);
        if(chat[5].equals(login)){
            dto.setMe(true);
            dto.setCompanion((String) chat[5]);
        }else{
            dto.setMe(false);
            dto.setCompanion((String) chat[6]);
        }
        return dto;
    }

    public List<ChatDTO> toDtoList(List<Object[]> chats, String login){
        List<ChatDTO> list = new ArrayList<>();
        for (Object[] chat : chats) {
            list.add(toDto(chat, login));
        }
        return list;
    }
}
