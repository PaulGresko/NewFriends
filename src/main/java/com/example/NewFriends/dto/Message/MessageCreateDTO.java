package com.example.NewFriends.dto.Message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MessageCreateDTO {
    private final String sender;
    private final String recipient;
    private final String text;

    public MessageCreateDTO(
                            @JsonProperty("sender") String sender,
                            @JsonProperty("recipient") String recipient,
                            @JsonProperty("text") String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }
}
