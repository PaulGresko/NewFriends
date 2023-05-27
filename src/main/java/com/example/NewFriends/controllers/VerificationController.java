package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.VerificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/verifications")
public class VerificationController {

private final VerificationService verificationService;

@Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping
    public ResponseEntity<List<UserDataDTO>> unverifiedUsers(){
        return ResponseEntity.ok(verificationService.findUnverifiedUsers());
    }

    @PatchMapping("/{login}")
    public ResponseEntity<UserDTO> verificationUser(@PathVariable String login){
        return ResponseEntity.ok(verificationService.verify(login));
    }

    @PatchMapping("delete/{login}")
    public ResponseEntity<UserDTO> unverificationUser(@PathVariable String login){
        return ResponseEntity.ok(verificationService.unverify(login));
    }
}
