package com.example.NewFriends.dto.complaint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ComplaintDTO {
    private final int id;
    private final Date date;
    private final Date time;
    private final String sender;
    private final String victim;
    private final String text;
    private final String name;
    private final String description;
    private final String sex;
    private final byte[] image;
    private final int year;
    private final String city;
    private final String zodiacSign;
}
