package com.example.NewFriends.controllers;


import com.example.NewFriends.dto.complaint.ComplaintCreateDTO;
import com.example.NewFriends.dto.userData.CategoryDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.security.JWTService;
import com.example.NewFriends.services.ComplaintService;
import com.example.NewFriends.services.UserDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/findFriends")
public class UserDataController {

    private final UserDataService userDataService;
    private final ComplaintService complaintService;

    @Autowired
    public UserDataController(UserDataService userDataService, ComplaintService complaintService) {
        this.userDataService = userDataService;
        this.complaintService = complaintService;
    }

    @GetMapping
    public ResponseEntity<UserDataDTO> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(userDataService.findUser(request));
    }

    @PatchMapping("/send/{login}")
    public ResponseEntity<UserDataDTO> sendFriendRequest(@PathVariable String login, HttpServletRequest request){
        userDataService.addNewFriend(request, login);
        return ResponseEntity.ok(userDataService.findUser(request));
    }
    @PostMapping("/complain")
    public ResponseEntity<UserDataDTO> complain(HttpServletRequest request,@RequestBody ComplaintCreateDTO dto){
        complaintService.create(request,dto);
        return ResponseEntity.ok(userDataService.findUser(request));
    }













    @GetMapping("/{login}")
    public ResponseEntity<UserDataDTO> findByLogin(@PathVariable String login){
        return ResponseEntity.ok(userDataService.findByLogin(login));
    }
//    @PatchMapping("/{login}")
//    public ResponseEntity<UserDataDTO> update(@PathVariable String login, @RequestBody UserDataDTO dto){
//        return ResponseEntity.ok(userDataService.update(login, dto));
//    }
//
//
//    @PatchMapping("/update")
//    public ResponseEntity<UserDataDTO> update(@RequestBody UserDataDTO dto, HttpServletRequest request){
//        String username = jwtService.getLogin(request);
//        return ResponseEntity.ok(userDataService.update(username, dto));
//    }

    @GetMapping("/category")
    public ResponseEntity<List<UserDataDTO>> findByCategory(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(userDataService.findByCategory(dto));
    }



}
