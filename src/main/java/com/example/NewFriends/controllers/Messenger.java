package com.example.NewFriends.controllers;


import com.example.NewFriends.dto.Message.ChatDTO;
import com.example.NewFriends.dto.Message.MessageCreateDTO;
import com.example.NewFriends.dto.Message.MessageDTO;
import com.example.NewFriends.services.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/messenger")
public class Messenger {

    private final MessageService messageService;

    public Messenger(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public ResponseEntity<List<ChatDTO>> getAllChats(HttpServletRequest request){
        return ResponseEntity.ok(messageService.findAllChats(request));
    }
    @GetMapping("/{login}")
    public ResponseEntity<List<MessageDTO>> getAllMessages(HttpServletRequest request, @PathVariable String login){
        return ResponseEntity.ok(messageService.findAllMessages(request, login));
    }
    @PutMapping()
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageCreateDTO dto){
        return ResponseEntity.ok(messageService.save(dto));
    }


}
