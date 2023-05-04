package com.example.springTestAplication.dto.friends;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FriendsDTO {
    private final Long id;
    private final String friend1;
    private final String friend2;
    private final String status;
}
