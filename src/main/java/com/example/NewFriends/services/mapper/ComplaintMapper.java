package com.example.NewFriends.services.mapper;


import com.example.NewFriends.dto.complaint.ComplaintDTO;
import com.example.NewFriends.entity.Complaint;
import com.example.NewFriends.util.CalculateAge;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComplaintMapper {
    public ComplaintDTO toDto(Object[] complaint){
        return new ComplaintDTO(
                (int) complaint[0],
                (Date) complaint[1],
                (Date) complaint[2],
                String.valueOf(complaint[3]),
                String.valueOf(complaint[4]),
                String.valueOf(complaint[5]),
                String.valueOf(complaint[6]),
                String.valueOf(complaint[7]),
                String.valueOf(complaint[8]),
                (byte[]) complaint[9],
                CalculateAge.get((Date)complaint[10]),
                String.valueOf(complaint[11]),
                String.valueOf(complaint[12])
        );
    }
    public List<ComplaintDTO> toDtoList(List<Object[]> complaints){
        return complaints.stream().map(this::toDto).collect(Collectors.toList());
    }
}
