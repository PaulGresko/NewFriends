package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.UserDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Me")
public class MyAccountController {

    private final UserDataService userDataService;

    public MyAccountController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/myData")
    public ResponseEntity<UserDataDTO> findMyData(HttpServletRequest request){
        return ResponseEntity.ok(userDataService.findMyData(request));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDataDTO> create(@RequestBody UserDataDTO dto){
        return ResponseEntity.ok(userDataService.save(dto));
    }
}
