package com.example.NewFriends.dto.complaint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ComplaintCreateDTO {
    private final String sender;
    private final String victim;
    private final String text;

    public ComplaintCreateDTO(@JsonProperty("sender") String sender,
                              @JsonProperty("victim") String victim,
                              @JsonProperty("text")   String text) {
        this.sender = sender;
        this.victim = victim;
        this.text = text;
    }
}
