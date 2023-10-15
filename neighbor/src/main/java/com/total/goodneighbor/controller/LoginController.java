package com.total.goodneighbor.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.total.goodneighbor.entity.userinformation;
import com.total.goodneighbor.mapper.UserMapper;
import com.total.goodneighbor.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController extends ServiceImpl<UserMapper, userinformation>{
    @Autowired
    private LoginService loginService;
    private String Email;

    @PostMapping("/login")
    public ResponseEntity<String> loginRequest(@RequestBody String email) {
        loginService.saveemail(email);
        System.out.println(email);
        Email=email;
        return new ResponseEntity<>("接受邮箱成功", HttpStatus.OK);
    }
    @PostMapping("/real")
    public ResponseEntity<String> Request(@RequestBody String realname) {
        userinformation list = baseMapper.selectById(Email);
        list.setRealname(realname);
        baseMapper.updateById(list);
        System.out.println(realname);
        return new ResponseEntity<>("接受真实姓名成功", HttpStatus.OK);
    }
    public ResponseEntity<Integer> handleRequest(@RequestBody int id) {
        userinformation list = baseMapper.selectById(Email);
        list.setId(id);
        baseMapper.updateById(list);
        System.out.println(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
