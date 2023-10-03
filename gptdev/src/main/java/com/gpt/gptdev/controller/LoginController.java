package com.gpt.gptdev.controller;

import com.gpt.gptdev.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/test")
    public void User(){
        System.out.println("OK");
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleRequest(@RequestBody String email) {
        loginService.saveUser(email);
        System.out.println(email);
        return new ResponseEntity<>("接受邮箱成功", HttpStatus.OK);
    }

}
