package com.example.NewFriends.services.impl;

import com.example.NewFriends.dto.complaint.ComplaintCreateDTO;
import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.entity.Friends;
import com.example.NewFriends.entity.User;
import com.example.NewFriends.entity.UserData;
import com.example.NewFriends.repositories.ComplaintRepository;
import com.example.NewFriends.repositories.FriendsRepository;
import com.example.NewFriends.repositories.UserDataRepository;
import com.example.NewFriends.repositories.UserRepository;
import com.example.NewFriends.security.JWTService;
import com.example.NewFriends.services.ComplaintService;
import com.example.NewFriends.services.mapper.ComplaintMapper;
import com.example.NewFriends.util.enums.FriendsStatus;
import com.example.NewFriends.util.enums.Status;
import jakarta.servlet.http.HttpServletRequest;
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
    private final FriendsRepository friendsRepository;
    private final JWTService jwtService;

    @Autowired
    public ComplaintServiceImpl(ComplaintRepository complaintRepository, UserDataRepository userDataRepository, ComplaintMapper complaintMapper, UserRepository userRepository, FriendsRepository friendsRepository, JWTService jwtService) {
        this.complaintRepository = complaintRepository;
        this.userDataRepository = userDataRepository;
        this.complaintMapper = complaintMapper;
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
        this.jwtService = jwtService;
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
    public void create(HttpServletRequest request,ComplaintCreateDTO complaintDTO) {
        String login = jwtService.getLogin(request);
        UserData sender = userDataRepository.findById(login).orElseThrow(()-> new NoSuchElementException("Complaint`s sender not found"));
        UserData recipient = userDataRepository.findById(complaintDTO.getVictim()).orElseThrow(()-> new NoSuchElementException("Complaint`s victim not found"));

        Complaint complaint = new Complaint();
        complaint.setSender(sender);
        complaint.setVictim(recipient);
        complaint.setText(complaintDTO.getText());
        complaint.setDate(new Date());
        complaint.setTime(new Date());
        complaintRepository.save(complaint);


        Friends friends = new Friends();
        friends.setFriend1(sender);
        friends.setFriend2(recipient);
        friends.setStatus(FriendsStatus.lock);
        friendsRepository.save(friends);

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
