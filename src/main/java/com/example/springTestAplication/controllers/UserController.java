package com.example.springTestAplication.controllers;

import com.example.springTestAplication.dto.user.AuthDTO;
import com.example.springTestAplication.dto.userData.UserDataDTO;
import com.example.springTestAplication.services.*;
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
    private final MessageService messageService;
    private final ComplaintService complaintService;
    private final FriendsService friendsService;

    @Autowired
    public UserController(UserService usersService,
                          UserDataService userDataService,
                          MessageService messageService,
                          ComplaintService complaintService,
                          FriendsService friendsService) {
        this.usersService = usersService;
        this.userDataService = userDataService;
        this.messageService = messageService;
        this.complaintService = complaintService;
        this.friendsService = friendsService;
    }




    @GetMapping(value = "users",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuthDTO> index(){
        return usersService.findAll();
    }

    @GetMapping("/api/usersData")
    public List<UserDataDTO> show(){
        return userDataService.findAll();
    }

    @GetMapping("/{login}")
    public AuthDTO show(@PathVariable("login") String login){

        return usersService.findByLogin(login);
   }



    @GetMapping("/new")
    public String newUser(){

        return "users/new";
    }

//    @PostMapping()
//    public String create(@RequestBody RegistrationDTO user){
//
//        usersService.save(user);
//        return "redirect:/users";
//    }

    @GetMapping("/{login}/edit")
    public String edit(Model model, @PathVariable("login") String login){
        model.addAttribute("user", usersService.findByLogin(login));
        return "users/edit";
    }

//    @PatchMapping("/{login}")
//    public String udpate(@RequestBody RegistrationDTO user, BindingResult bindingResult ,
//                         @PathVariable("login") String login ){
//        if(bindingResult.hasErrors())
//            return "users/edit";
//
//        usersService.update(login, user);
//        return "redirect:/users";
//    }

    @DeleteMapping("/{login}")
    public String delete(@PathVariable("login") String login){
        usersService.delete(login);
        return "redirect:/users";
    }
}
