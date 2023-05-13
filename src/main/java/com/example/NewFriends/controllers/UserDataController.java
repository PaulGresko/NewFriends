package com.example.NewFriends.controllers;


import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FindFriends")
public class UserDataController {

    private final UserDataService userDataService;

    @Autowired
    public UserDataController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping
    public ResponseEntity<List<UserDataDTO>> findAll() {
        return ResponseEntity.ok(userDataService.findAll());
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDataDTO> findByLogin(@PathVariable String login){
        return ResponseEntity.ok(userDataService.findByLogin(login));
    }
    @PatchMapping("/{login}")
    public ResponseEntity<UserDataDTO> update(@PathVariable String login, @RequestBody UserDataDTO dto){
        Object i = new Object();
        System.out.println("i = " + i);

        return ResponseEntity.ok(userDataService.update(login, dto));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDataDTO> create(@RequestBody UserDataDTO dto){
        return ResponseEntity.ok(userDataService.save(dto));
    }

}
