package com.example.NewFriends.dto.complaint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ComplaintCreateDTO {
    private final String victim;
    private final String text;

    public ComplaintCreateDTO(@JsonProperty("victim") String victim,
                              @JsonProperty("text")   String text) {
        this.victim = victim;
        this.text = text;
    }
}
