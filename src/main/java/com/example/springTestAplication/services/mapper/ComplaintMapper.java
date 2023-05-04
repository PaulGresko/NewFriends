package com.example.springTestAplication.services.mapper;


import com.example.springTestAplication.dto.complaint.ComplaintDTO;
import com.example.springTestAplication.entity.Complaint;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComplaintMapper {
    public ComplaintDTO toDto(Complaint complaint){
        return new ComplaintDTO(complaint.getId(),
                complaint.getDate(),
                complaint.getTime(),
                complaint.getSender().getLogin(),
                complaint.getVictim().getLogin(),
                complaint.getText());
    }
    public List<ComplaintDTO> toDtoList(List<Complaint> complaints){
        return complaints.stream().map(this::toDto).collect(Collectors.toList());
    }
}
