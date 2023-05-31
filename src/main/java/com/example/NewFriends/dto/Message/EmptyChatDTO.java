package com.example.NewFriends.dto.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class EmptyChatDTO {
    String companion;
    String companionName;
    byte[] image;
}
