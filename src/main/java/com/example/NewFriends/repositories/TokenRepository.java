package com.example.NewFriends.repositories;

import com.example.NewFriends.entity.Token;
import com.example.NewFriends.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {

    List<Token> findAllByExpiredAndUser(Boolean expired, User user);
    Optional<Token> findByToken(String token);
}
