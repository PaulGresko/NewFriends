package com.example.NewFriends.dto.friends;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FriendsCreateDTO {
    private final String friend1;
    private final String friend2;
    private final String status;

    public FriendsCreateDTO(@JsonProperty("friend1") String friend1,
                            @JsonProperty("friend2") String friend2,
                            @JsonProperty("status") String status) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.status = status;
    }
}
