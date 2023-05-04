package com.example.springTestAplication.dto.complaint;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ComplaintDTO {
    private final Long id;
    private final Date date;
    private final Date time;
    private final String sender;
    private final String victim;
    private final String text;
}
