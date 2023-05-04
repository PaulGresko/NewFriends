package com.example.springTestAplication.services.impl;

import com.example.springTestAplication.dto.complaint.ComplaintCreateDTO;
import com.example.springTestAplication.dto.complaint.ComplaintDTO;
import com.example.springTestAplication.entity.Complaint;
import com.example.springTestAplication.entity.UserData;
import com.example.springTestAplication.repositories.ComplaintRepository;
import com.example.springTestAplication.repositories.UserDataRepository;
import com.example.springTestAplication.services.ComplaintService;
import com.example.springTestAplication.services.mapper.ComplaintMapper;
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

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository, UserDataRepository userDataRepository, ComplaintMapper complaintMapper) {
        this.complaintRepository = complaintRepository;
        this.userDataRepository = userDataRepository;
        this.complaintMapper = complaintMapper;
    }


    @Override
    public List<ComplaintDTO> findAll() {
        return complaintMapper.toDtoList(complaintRepository.findAll());
    }

    @Override
    public List<ComplaintDTO> findBySender(String login) {
        UserData user = userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("Message`s sender not found"));
        return complaintMapper.toDtoList(complaintRepository.findAllBySender(user));
    }

    @Override
    public List<ComplaintDTO> findByVictim(String login) {
        UserData user = userDataRepository.findById(login).orElseThrow(()->new NoSuchElementException("Message`s recipient not found"));
        return complaintMapper.toDtoList(complaintRepository.findAllBySender(user));
    }

    @Override
    @Transactional
    public ComplaintDTO save(ComplaintCreateDTO complaintDTO) {
        Complaint complaint = new Complaint();
        complaint.setSender(userDataRepository.findById(complaintDTO.getSender()).orElseThrow(()-> new NoSuchElementException("Complaint`s sender not found")));
        complaint.setVictim(userDataRepository.findById(complaintDTO.getVictim()).orElseThrow(()-> new NoSuchElementException("Complaint`s victim not found")));
        complaint.setText(complaintDTO.getText());
        complaint.setDate(new Date());
        complaint.setTime(new Date());
        return complaintMapper.toDto(complaintRepository.save(complaint));
    }

    @Override
    @Transactional
    public ComplaintDTO update(Long id, ComplaintCreateDTO complaintDTO) {
        Complaint complaint = complaintRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Complaint not found"));
        complaint.setText(complaintDTO.getText());
        return complaintMapper.toDto(complaintRepository.save(complaint));
    }

    @Override
    public String delete(Long id) {
        complaintRepository.deleteById(id);
        return "User was successful deleted!";
    }
}
