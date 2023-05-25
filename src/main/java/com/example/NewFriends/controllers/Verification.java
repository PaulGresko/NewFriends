package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.util.enums.Status;
import com.example.NewFriends.services.UserDataService;
import com.example.NewFriends.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/verifications")
public class Verification {

    private final UserService userService;
    private final UserDataService userDataService;
    @Autowired
    public Verification(UserService userService, UserDataService userDataService) {
        this.userService = userService;
        this.userDataService = userDataService;
    }


    @GetMapping
    public ResponseEntity<List<UserDataDTO>> unverifiedUsers(){
        return ResponseEntity.ok(userDataService.findUnverifiedUsers());
    }

    @PatchMapping("/{login}")
    public ResponseEntity<String> verificationUser(@PathVariable String login){
        User user = userService.findUserByLogin(login);
        user.setStatus(Status.ROLE_USER);
        return ResponseEntity.ok(userService.update(login,user).getStatus());
    }

}
