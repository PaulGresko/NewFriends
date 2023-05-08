package com.example.NewFriends.repositories;


import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findAllBySender(UserData user);
    List<Complaint> findAllByVictim(UserData user);
}
