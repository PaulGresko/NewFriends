package com.example.NewFriends.services;



import com.example.NewFriends.dto.complaint.ComplaintCreateDTO;
import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;

import java.util.List;

public interface ComplaintService {

    Complaint findById(Long id);
    List<ComplaintDTO> findAll();
    List<ComplaintDTO> findBySender(String login);
    List<ComplaintDTO> findByVictim(String login);
    ComplaintDTO save(ComplaintCreateDTO complaintDTO);
    ComplaintDTO update(Long id, Complaint complaint);
    String delete(Long id);
}
