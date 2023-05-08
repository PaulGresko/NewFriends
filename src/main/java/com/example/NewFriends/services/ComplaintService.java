package com.example.NewFriends.services;



import com.example.NewFriends.dto.complaint.ComplaintCreateDTO;
import com.example.NewFriends.dto.complaint.ComplaintDTO;

import java.util.List;

public interface ComplaintService {

    List<ComplaintDTO> findAll();
    List<ComplaintDTO> findBySender(String login);
    List<ComplaintDTO> findByVictim(String login);
    ComplaintDTO save(ComplaintCreateDTO complaintDTO);
    ComplaintDTO update(Long id, ComplaintCreateDTO complaintDTO);
    String delete(Long id);
}
