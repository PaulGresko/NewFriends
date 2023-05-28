package com.example.NewFriends.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name="last_find")
public class LastFind {

    @Id
    String login;


    @Column(name = "last_found_user")
    String lastFindUser;
}
