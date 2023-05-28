package com.example.NewFriends.repositories;

import com.example.NewFriends.entity.LastFind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LastFindRepository extends JpaRepository<LastFind, String> {


    @Modifying
    @Query(value = "update last_find set\n" +
            "                     last_found_user = :name\n" +
            "where login = :username",
    nativeQuery = true)
    void updateLastFoundUser(String username, String name);
}
