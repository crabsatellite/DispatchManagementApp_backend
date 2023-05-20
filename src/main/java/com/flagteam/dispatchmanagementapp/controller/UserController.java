package com.flagteam.dispatchmanagementapp.controller;
import com.flagteam.dispatchmanagementapp.model.Token;
import com.flagteam.dispatchmanagementapp.model.User;
import com.flagteam.dispatchmanagementapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/v1/users/register")
    public void addUser(@RequestBody User user) {userService.add(user);}


    @PostMapping("/api/v1/users/login")
    public Token verifyUser(@RequestBody User user) {return userService.authenticate(user);}
}
