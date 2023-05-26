package com.example.NewFriends.dto.Message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatDTO {
    private String companionName;
    private byte[] image;
    private String companion;
    private String text;
    private boolean me;
}
