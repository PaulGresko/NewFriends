package com.example.NewFriends.services.impl;

import com.example.NewFriends.dto.Authentication.UserDTO;
import com.example.NewFriends.dto.complaint.ComplaintCreateDTO;
import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.entity.UserData;
import com.example.NewFriends.repositories.ComplaintRepository;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.services.ComplaintService;
import com.example.NewFriends.services.mapper.ComplaintMapper;
import com.example.NewFriends.util.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserDataRepository userDataRepository;
    private final ComplaintMapper complaintMapper;
    private final UserRepository userRepository;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository, UserDataRepository userDataRepository, ComplaintMapper complaintMapper, UserRepository userRepository) {
        this.complaintRepository = complaintRepository;
        this.userDataRepository = userDataRepository;
        this.complaintMapper = complaintMapper;
        this.userRepository = userRepository;
    }


    @Override
    public Complaint findById(Long id) {
        return complaintRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Complaint not found"));
    }

    @Override
    public List<ComplaintDTO> findAll() {
        return complaintMapper.toDtoList(complaintRepository.findAllComplaints());
    }



    @Override
    @Transactional
    public void save(ComplaintCreateDTO complaintDTO) {
        Complaint complaint = new Complaint();
        complaint.setSender(userDataRepository.findById(complaintDTO.getSender()).orElseThrow(()-> new NoSuchElementException("Complaint`s sender not found")));
        complaint.setVictim(userDataRepository.findById(complaintDTO.getVictim()).orElseThrow(()-> new NoSuchElementException("Complaint`s victim not found")));
        complaint.setText(complaintDTO.getText());
        complaint.setDate(new Date());
        complaint.setTime(new Date());
    }

    @Override
    @Transactional
    public void ban(Long id) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Complaint not found"));
        User user = userRepository.findById(complaint.getVictim().getLogin()).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setStatus(Status.ROLE_LOCK);
        user = userRepository.save(user);
        complaintRepository.deleteAllByVictim(user.getLogin());
    }

    @Override
    @Transactional
    public void unban(Long id) {
        complaintRepository.deleteById(id);
    }
}
