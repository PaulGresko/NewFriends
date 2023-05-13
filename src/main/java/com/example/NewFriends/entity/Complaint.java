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
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @Column
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh/mm/ss")
    private Date time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sender",referencedColumnName = "login")
    private UserData sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="victim",referencedColumnName = "login")
    private UserData victim;
    @Column
    private String text;
    @Column
    private boolean checked;


}
