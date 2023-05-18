package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.enums.Status;
import com.example.NewFriends.services.ComplaintService;
import com.example.NewFriends.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;
    private final UserService userService;

    @Autowired
    public ComplaintController(ComplaintService complaintService, UserService userService) {
        this.complaintService = complaintService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintDTO>> allComplaints(){
        return ResponseEntity.ok(complaintService.findAll());
    }
    @GetMapping("/{login}")
    public ResponseEntity<List<ComplaintDTO>> complaint(@PathVariable String login){
        return ResponseEntity.ok(complaintService.findByVictim(login));
    }
    @PatchMapping("/{complaint_ID}")
    public void banUser(@PathVariable String complaint_ID){ // todo надо проверить

        Complaint complaint = complaintService.findById(Long.valueOf(complaint_ID));
        complaint.setChecked(true);
        complaintService.update(complaint.getId(),complaint);


        User user = userService.findUserByLogin(complaint.getVictim().getLogin());
        user.setStatus(Status.ROLE_LOCK);
        userService.update(user.getLogin(), user);
    }
}
