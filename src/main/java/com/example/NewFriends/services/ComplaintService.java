package com.example.NewFriends.services;



import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.complaint.ComplaintCreateDTO;
import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ComplaintService {

    Complaint findById(Long id);
    List<ComplaintDTO> findAll();
//    List<ComplaintDTO> findBySender(String login);
//    List<ComplaintDTO> findByVictim(String login);
    void create(HttpServletRequest request,ComplaintCreateDTO complaintDTO);
    void ban(Long id);
    void unban(Long id);
}
