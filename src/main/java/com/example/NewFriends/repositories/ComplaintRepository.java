package com.example.NewFriends.repositories;


import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findAllBySender(UserData user);
    List<Complaint> findAllByVictim(UserData user);

    @Query(value = "delete from complaint where victim = :victim",
    nativeQuery = true)
    @Modifying
    void deleteAllByVictim(String victim);

    @Query(value = "delete from complaint where id = :id",
            nativeQuery = true)
    @Modifying
    void deleteById(Long id);
}
