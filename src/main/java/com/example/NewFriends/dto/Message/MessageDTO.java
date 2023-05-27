package com.example.NewFriends.dto.Message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class MessageDTO {
    private final boolean me;
    private final String text;
    private final String dateTime;

}
