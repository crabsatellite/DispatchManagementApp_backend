package com.flagteam.dispatchmanagementapp.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagteam.dispatchmanagementapp.model.Token;
import com.flagteam.dispatchmanagementapp.model.User;
import com.flagteam.dispatchmanagementapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
