package com.example.NewFriends.controllers;


import com.example.NewFriends.dto.userData.CategoryDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserData")
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
        return ResponseEntity.ok(userDataService.update(login, dto));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDataDTO> create(@RequestBody UserDataDTO dto){
        return ResponseEntity.ok(userDataService.save(dto));
    }

    @GetMapping("/category")
    public ResponseEntity<List<UserDataDTO>> findByCategory(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(userDataService.findByCategory(dto));
    }

}
