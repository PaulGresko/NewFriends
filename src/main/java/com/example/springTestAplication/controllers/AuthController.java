package com.example.springTestAplication.controllers;


import com.example.springTestAplication.dto.user.AuthDTO;
import com.example.springTestAplication.dto.user.RegistrationDTO;
import com.example.springTestAplication.entity.User;
import com.example.springTestAplication.enums.Status;
import com.example.springTestAplication.security.JWTUtil;
import com.example.springTestAplication.services.RegistrationService;
import com.example.springTestAplication.services.UserService;
import com.example.springTestAplication.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService, JWTUtil jwtUtil, UserMapper userMapper, AuthenticationManager authenticationManager, UserService userService) {
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    //    @GetMapping(value = "/login")
//    public String loginPage(){
//        return "auth/login";
//    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){ // todo Поменять на DTO

        return "auth/registration";
    }
    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody RegistrationDTO reg){

        User user = userMapper.fromDto(reg);

        registrationService.register(user);
        return Map.of("jwt-token", jwtUtil.generateToken(reg.getLogin(), Status.ROLE_NEW.toString()));
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "/auth/admin";
    }

    @PostMapping("/login")
    public Map<String,String> performLogin(@RequestBody AuthDTO authDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getLogin(),authDTO.getPassword()));
        User user = userService.findUserByLogin(authDTO.getLogin());


        if(authentication.isAuthenticated()){
            Map<String,String> map = new HashMap<>();
            map.put("JWT", jwtUtil.generateToken(authDTO.getLogin(), user.getStatus().toString()));
            map.put("Authorities",user.getStatus().toString());
            return map;
        }
        else{
            return Map.of("message", "Incorrect credentials!");
        }
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (User)authentication.getPrincipal();

        return userDetails.getAuthorities().stream().toString() + "\n" + userDetails.getUsername() + "\n" + userDetails.getPassword();
    }
}
