package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.friends.FriendsDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.FriendsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }


    @GetMapping()
    public ResponseEntity<List<UserDataDTO>> getAllFriends(HttpServletRequest request){
        return ResponseEntity.ok(friendsService.findAllFriends(request));
    }
    @PutMapping("/{login}")
    public ResponseEntity<FriendsDTO> friendsRequest(HttpServletRequest request,@PathVariable String login){
        return ResponseEntity.ok(friendsService.friendRequest(request,login));
    }

    @GetMapping("/invites")
    public ResponseEntity<List<UserDataDTO>> allInvites(HttpServletRequest request){
        return ResponseEntity.ok(friendsService.findAllInvites(request));
    }

    @GetMapping("/requests")
    public ResponseEntity<List<UserDataDTO>> allRequest(HttpServletRequest request){
        return ResponseEntity.ok(friendsService.findAllRequests(request));
    }

    @PatchMapping("/invites/{login}")
    public ResponseEntity<Map<String,String>> takeRequests(@PathVariable String login, HttpServletRequest request){
        friendsService.acceptRequest(request,login);
        return ResponseEntity.ok(Map.of("Message", "Request accepted"));
    }

    @PatchMapping("/invites/lock/{login}")
    public ResponseEntity<Map<String,String>> cancelRequest(@PathVariable String login, HttpServletRequest request){


        return ResponseEntity.ok(Map.of("Message", "Request accepted"));
    }

}
