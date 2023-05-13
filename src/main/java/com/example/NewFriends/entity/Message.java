package com.example.NewFriends.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @Column(name="time")
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh/mm/ss")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sender",referencedColumnName = "login")
    private UserData sender;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient",referencedColumnName = "login")
    private UserData recipient;
    @Column(name = "text")
    private String text;


    public Message(UserData sender, UserData recipient, String text) {
        this.date = new Date();
        this.time = new Date();
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }
}
