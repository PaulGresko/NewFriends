package com.example.NewFriends.entity;

import com.example.NewFriends.util.enums.Sex;
import com.example.NewFriends.util.enums.ZodiacSign;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "user_data")
public class UserData {
    @Id
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name="sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(name="description")
    private String description;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    @Column(name="city")
    private String city;
    @Column(name = "zodiac_sign")
    @Enumerated(EnumType.STRING)
    private ZodiacSign zodiacSign;


    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "login",referencedColumnName = "login")
    private User user;


    @ToString.Exclude
    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Complaint> sentComplaints = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "victim",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Complaint> complaintsReceived = new ArrayList<>();



    @ToString.Exclude
    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> sentMessages = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "recipient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Message> messagesReceived = new ArrayList<>();



    @ToString.Exclude
    @OneToMany(mappedBy = "friend1",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Friends> friends1 = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "friend2",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Friends> friends2 = new ArrayList<>();


//    @ToString.Exclude
//    @OneToOne(mappedBy = "login")
//    private LastFind lastFind;

}
