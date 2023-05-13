package com.example.NewFriends.controllers;

import com.example.NewFriends.dto.Authentication.AuthDTO;
import com.example.NewFriends.dto.userData.UserDataDTO;
import com.example.NewFriends.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/")
public class UserController {

    private final UserService usersService;
    private final UserDataService userDataService;

    @Autowired
    public UserController(UserService usersService,
                          UserDataService userDataService) {
        this.usersService = usersService;
        this.userDataService = userDataService;

    }




    @GetMapping(value = "users",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuthDTO> index(){
        return usersService.findAll();
    }

    @GetMapping("/api/usersData")
    public List<UserDataDTO> show(){
        return userDataService.findAll();
    }

    @GetMapping("users/{login}")
    public AuthDTO show(@PathVariable("login") String login){

        return usersService.findByLogin(login);
   }



    @GetMapping("/new")
    public String newUser(){

        return "users/new";
    }


    @GetMapping("/{login}/edit")
    public String edit(Model model, @PathVariable("login") String login){
        model.addAttribute("user", usersService.findByLogin(login));
        return "users/edit";
    }



    @DeleteMapping("/{login}")
    public String delete(@PathVariable("login") String login){
        usersService.delete(login);
        return "redirect:/users";
    }
}
