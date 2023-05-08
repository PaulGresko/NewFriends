package com.example.NewFriends.dto.Message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class MessageDTO {
    private final Long id;
    private final Date date;
    private final Date time;
    private final String sender;
    private final String recipient;
    private final String text;
}
