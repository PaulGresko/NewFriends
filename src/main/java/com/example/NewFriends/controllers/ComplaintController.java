package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.util.enums.Status;
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

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintDTO>> allComplaints(){
        return ResponseEntity.ok(complaintService.findAll());
    }
//    @GetMapping("/{login}")
//    public ResponseEntity<List<ComplaintDTO>> complaint(@PathVariable String login){
//        return ResponseEntity.ok(complaintService.findByVictim(login));
//    }
    @PatchMapping("/ban/{complaint_ID}")
    public void banUser(@PathVariable String complaint_ID){ // todo надо проверить
        complaintService.ban(Long.valueOf(complaint_ID));
    }

    @PatchMapping("/unban/{complaint_ID}")
    public void unbanUser(@PathVariable String complaint_ID){
        complaintService.unban(Long.valueOf(complaint_ID));
    }
}
