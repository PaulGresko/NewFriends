package com.example.NewFriends.entity;

import com.example.NewFriends.util.enums.FriendsStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend1",referencedColumnName = "login")
    private UserData friend1;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend2",referencedColumnName = "login")
    private UserData friend2;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private FriendsStatus status;
}
