package com.gpt.gptdev.controller;

import com.gpt.gptdev.entity.User;
import com.gpt.gptdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userT")
    public void User(){
        List<User> UserList = userService.getUser("1");
        System.out.println(UserList);
        System.out.println("123");
    }
}
