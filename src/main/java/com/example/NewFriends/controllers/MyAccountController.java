package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.userData.UserDataCreateDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.UserDataService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/Me")
public class MyAccountController {

    private final UserDataService userDataService;

    public MyAccountController(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @GetMapping("/myData")
    public ResponseEntity<UserDataDTO> findMyData(HttpServletRequest request){
        return ResponseEntity.ok(userDataService.findMyData(request));
    }

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDataDTO> create(HttpServletRequest request,
                                              @RequestPart(name = "name", required = false) String name,
                                              @RequestPart(name = "image", required = false) byte[] image,
                                              @RequestPart(name = "sex", required = false) String sex,
                                              @RequestPart(name = "description", required = false) String description,
                                              @RequestPart(name = "birthdate", required = false) String birthdate,
                                              @RequestPart(name = "city", required = false) String city) throws ParseException {

        //2002-06-05

        System.out.println(birthdate);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(image.length);
        UserDataCreateDTO dto = new UserDataCreateDTO(name, image, sex, description, format.parse(birthdate), city);
        return ResponseEntity.ok(userDataService.save(request, dto));
    }

}
